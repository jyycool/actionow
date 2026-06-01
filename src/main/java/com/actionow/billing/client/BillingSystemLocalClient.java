package com.actionow.billing.client;

import com.actionow.common.core.result.Result;
import com.actionow.system.controller.SystemInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Billing 模块访问 System 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class BillingSystemLocalClient implements SystemLocalClient {

    private final ObjectProvider<SystemInternalController> systemInternalControllerProvider;

    private SystemInternalController systemInternalController() {
        return systemInternalControllerProvider.getObject();
    }

    @Override
    public Result<String> getConfigValue(String configKey) {
        return systemInternalController().getConfigValue(configKey);
    }
}
