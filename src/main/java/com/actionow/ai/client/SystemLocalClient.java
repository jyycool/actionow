package com.actionow.ai.client;

import com.actionow.common.core.result.Result;

/**
 * System 服务 本地客户端
 * 用于获取系统配置（如 API Keys）
 *
 * @author Actionow
 */
public interface SystemLocalClient {

    /**
     * 获取全局配置值
     *
     * @param configKey 配置键
     * @return 配置值
     */
    Result<String> getConfigValue(String configKey);
}
