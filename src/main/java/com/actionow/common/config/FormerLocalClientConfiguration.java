package com.actionow.common.config;

import com.actionow.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Registers local, in-process implementations for interfaces that used to be
 * HTTP clients in the multi-service layout.
 *
 * <p>The single-node application no longer uses Spring Cloud or HTTP service
 * discovery. These adapters keep existing injection points working while the
 * former client interfaces are gradually replaced by explicit local facades.</p>
 */
@Slf4j
@Configuration
public class FormerLocalClientConfiguration implements BeanDefinitionRegistryPostProcessor {

    private static final String BASE_PACKAGE = "com.actionow";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*LocalClient")));

        for (BeanDefinition candidate : scanner.findCandidateComponents(BASE_PACKAGE)) {
            String className = candidate.getBeanClassName();
            if (className == null) {
                continue;
            }
            try {
                Class<?> clientType = ClassUtils.forName(className, getClass().getClassLoader());
                if (!clientType.isInterface()) {
                    continue;
                }
                String beanName = className + "#localAdapter";
                if (hasExistingBeanForType(registry, clientType) || registry.containsBeanDefinition(beanName)) {
                    continue;
                }
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(LocalClientFactoryBean.class);
                builder.addConstructorArgValue(clientType);
                registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
            } catch (ClassNotFoundException ex) {
                throw new IllegalStateException("Failed to load local client type: " + className, ex);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory beanFactory) {
        // no-op
    }

    private boolean hasExistingBeanForType(BeanDefinitionRegistry registry, Class<?> clientType) {
        for (String beanName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            if (beanDefinition instanceof RootBeanDefinition rootBeanDefinition
                    && rootBeanDefinition.hasBeanClass()
                    && clientType.isAssignableFrom(rootBeanDefinition.getBeanClass())) {
                return true;
            }
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null) {
                continue;
            }
            try {
                Class<?> beanClass = ClassUtils.forName(beanClassName, getClass().getClassLoader());
                if (clientType.isAssignableFrom(beanClass)) {
                    return true;
                }
            } catch (ClassNotFoundException ignored) {
                // Ignore unresolved bean classes and let normal context startup report them if needed.
            }
        }
        return false;
    }

    public static final class LocalClientFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

        private final Class<?> clientType;
        private ApplicationContext applicationContext;
        private Object proxy;

        public LocalClientFactoryBean(Class<?> clientType) {
            this.clientType = clientType;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        @Override
        public Object getObject() {
            if (proxy == null) {
                proxy = Proxy.newProxyInstance(
                        clientType.getClassLoader(),
                        new Class<?>[]{clientType},
                        new LocalClientInvocationHandler(clientType, applicationContext)
                );
            }
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return clientType;
        }
    }

    private static final class LocalClientInvocationHandler implements InvocationHandler {

        private final Class<?> clientType;
        private final ApplicationContext applicationContext;
        private final Map<Method, MethodTarget> cache = new ConcurrentHashMap<>();

        private LocalClientInvocationHandler(Class<?> clientType, ApplicationContext applicationContext) {
            this.clientType = clientType;
            this.applicationContext = applicationContext;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }

            MethodTarget target = cache.computeIfAbsent(method, this::findTarget);
            if (target != null) {
                try {
                    return target.method.invoke(target.bean, args == null ? new Object[0] : args);
                } catch (ReflectiveOperationException ex) {
                    return failure(method, "Local client target invocation failed: " + ex.getMessage());
                }
            }
            return failure(method, "No local target found for former HTTP client "
                    + clientType.getName() + "#" + method.getName());
        }

        private MethodTarget findTarget(Method clientMethod) {
            Object[] argsPlaceholder = new Object[clientMethod.getParameterCount()];
            for (String beanName : applicationContext.getBeanDefinitionNames()) {
                Object bean;
                try {
                    bean = applicationContext.getBean(beanName);
                } catch (BeansException ex) {
                    continue;
                }
                if (bean == null || Proxy.isProxyClass(bean.getClass())) {
                    continue;
                }
                Class<?> beanType = AopUtils.getTargetClass(bean);
                if (beanType == null || beanType.getName().startsWith("org.springframework")
                        || beanType.getName().startsWith("com.actionow.common.config.FormerLocalClientConfiguration")) {
                    continue;
                }
                for (Method candidate : beanType.getMethods()) {
                    if (isCompatible(clientMethod, candidate, argsPlaceholder)) {
                        return new MethodTarget(bean, candidate);
                    }
                }
            }
            return null;
        }

        private boolean isCompatible(Method clientMethod, Method candidate, Object[] argsPlaceholder) {
            if (!candidate.getName().equals(clientMethod.getName())) {
                return false;
            }
            if (candidate.getParameterCount() != clientMethod.getParameterCount()) {
                return false;
            }
            if (!clientMethod.getReturnType().isAssignableFrom(candidate.getReturnType())) {
                return false;
            }
            Class<?>[] clientParams = clientMethod.getParameterTypes();
            Class<?>[] candidateParams = candidate.getParameterTypes();
            for (int i = 0; i < clientParams.length; i++) {
                if (!candidateParams[i].isAssignableFrom(clientParams[i]) && !clientParams[i].isAssignableFrom(candidateParams[i])) {
                    return false;
                }
            }
            return Arrays.stream(candidate.getExceptionTypes()).noneMatch(ex -> !RuntimeException.class.isAssignableFrom(ex));
        }

        private Object failure(Method method, String message) {
            if (Result.class.isAssignableFrom(method.getReturnType())) {
                return Result.fail(message);
            }
            if (method.getReturnType() == boolean.class) {
                return false;
            }
            if (method.getReturnType().isPrimitive()) {
                return 0;
            }
            return null;
        }
    }

    private record MethodTarget(Object bean, Method method) {
    }
}
