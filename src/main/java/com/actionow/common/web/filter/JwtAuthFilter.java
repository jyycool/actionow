package com.actionow.common.web.filter;

import com.actionow.common.core.constant.CommonConstants;
import com.actionow.common.core.context.UserContext;
import com.actionow.common.core.context.UserContextHolder;
import com.actionow.common.core.security.InternalAuthProperties;
import com.actionow.common.core.security.InternalAuthUtils;
import com.actionow.common.security.jwt.JwtClaims;
import com.actionow.common.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 单机版 JWT 认证过滤器
 * 将客户端发来的 Authorization: Bearer <jwt> 转换为内部 UserContext，
 * 模拟原微服务架构中网关的职责。
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final InternalAuthProperties internalAuthProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Skip if internal token already present (request from internal service)
        if (request.getHeader(CommonConstants.HEADER_INTERNAL_AUTH_TOKEN) != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            // parseTokenAllowExpired: 签名仍校验，允许过期（单机版无网关刷新 token 场景）
            JwtClaims claims = jwtUtils.parseTokenAllowExpired(token);
            if (claims == null || claims.getUserId() == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String internalToken = InternalAuthUtils.generateInternalToken(
                    internalAuthProperties.getAuthSecret(),
                    claims.getUserId(),
                    claims.getWorkspaceId(),
                    claims.getTenantSchema(),
                    internalAuthProperties.getInternalTokenExpireSeconds()
            );

            // Wrap the request to inject internal auth header and user context headers
            MutableHttpServletRequest wrappedRequest = new MutableHttpServletRequest(request);
            wrappedRequest.addHeader(CommonConstants.HEADER_INTERNAL_AUTH_TOKEN, internalToken);
            if (claims.getUserId() != null) {
                wrappedRequest.addHeader(CommonConstants.HEADER_USER_ID, claims.getUserId());
            }
            if (claims.getUsername() != null) {
                wrappedRequest.addHeader(CommonConstants.HEADER_USERNAME, claims.getUsername());
            }
            if (claims.getRoles() != null && !claims.getRoles().isEmpty()) {
                wrappedRequest.addHeader(CommonConstants.HEADER_USER_ROLE,
                        String.join(",", claims.getRoles()));
            }
            if (claims.getWorkspaceId() != null) {
                wrappedRequest.addHeader(CommonConstants.HEADER_WORKSPACE_ID, claims.getWorkspaceId());
            }
            if (claims.getSessionId() != null) {
                wrappedRequest.addHeader(CommonConstants.HEADER_SESSION_ID, claims.getSessionId());
            }

            filterChain.doFilter(wrappedRequest, response);
        } catch (Exception e) {
            log.debug("JWT parse failed: {}", e.getMessage());
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Simple mutable request wrapper to add headers.
     */
    private static class MutableHttpServletRequest extends HttpServletRequestWrapper {

        private final Map<String, String> extraHeaders = new HashMap<>();

        MutableHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        void addHeader(String name, String value) {
            extraHeaders.put(name.toLowerCase(), value);
        }

        @Override
        public String getHeader(String name) {
            String extra = extraHeaders.get(name.toLowerCase());
            return extra != null ? extra : super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            String extra = extraHeaders.get(name.toLowerCase());
            if (extra != null) {
                return Collections.enumeration(Collections.singletonList(extra));
            }
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            java.util.List<String> names = Collections.list(super.getHeaderNames());
            extraHeaders.keySet().forEach(k -> {
                if (!names.contains(k)) names.add(k);
            });
            return Collections.enumeration(names);
        }
    }
}
