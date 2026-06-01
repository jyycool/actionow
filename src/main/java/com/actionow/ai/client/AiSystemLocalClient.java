package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import com.actionow.system.controller.SystemInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiSystemLocalClient implements SystemLocalClient {

    private final SystemInternalController systemInternalController;

    @Override
    public Result<String> getConfigValue(String configKey) {
        return systemInternalController.getConfigValue(configKey);
    }
}
