package com.actionow.system.service;

import java.util.Map;

/**
 * 系统内部服务（供单体应用内其他模块直接调用）
 * <p>
 * 替代原 SystemInternalController，去除 HTTP 调用开销，
 * 改为应用内直接方法调用。
 *
 * @author Actionow
 */
public interface SystemInternalService {

    /**
     * 记录统计数据
     *
     * @param metricType  指标类型
     * @param value       指标值
     * @param workspaceId 工作空间 ID（可选）
     */
    void recordStats(String metricType, Long value, String workspaceId);

    /**
     * 获取全局配置值
     * 用于获取敏感配置（如 API Keys）
     *
     * @param configKey 配置键
     * @return 配置值
     */
    String getConfigValue(String configKey);

    /**
     * 批量获取配置值（按 key 前缀）
     * 供各模块 RuntimeConfigService 启动时批量加载
     *
     * @param prefix 配置键前缀（如 "runtime.agent"）
     * @return configKey → configValue 映射
     */
    Map<String, String> getConfigBatch(String prefix);
}