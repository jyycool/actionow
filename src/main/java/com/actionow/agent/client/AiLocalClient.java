package com.actionow.agent.client;

import com.actionow.agent.client.dto.AvailableProviderResponse;
import com.actionow.agent.client.dto.LlmCredentialsResponse;
import com.actionow.agent.client.dto.LlmProviderResponse;
import com.actionow.agent.client.dto.ProviderExecutionResultResponse;
import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;

import java.util.List;

/**
 * AI 服务 本地客户端
 * Agent 服务调用 AI 服务执行 AI 工具和获取 LLM 配置
 *
 * 使用独立的断路器和超时配置，适应 AI 服务的长响应时间特性
 *
 * @author Actionow
 */
public interface AiLocalClient {

    // ==================== Provider 执行 API ====================

    /**
     * 执行 AI Provider
     *
     * @param request 执行请求
     * @return 执行结果
     */
    Result<ProviderExecutionResultResponse> executeProvider(ProviderExecuteRequest request);

    /**
     * 获取可用 AI Provider 列表
     *
     * @param providerType Provider 类型（IMAGE/VIDEO/AUDIO/TEXT）
     * @return Provider 列表
     */
    Result<List<AvailableProviderResponse>> getAvailableProviders(
            String providerType);

    /**
     * 获取 Provider 详情
     *
     * @param providerId Provider ID
     * @return Provider 详情
     */
    Result<AvailableProviderResponse> getProviderDetail(
            String providerId);

    /**
     * 查询执行状态
     *
     * @param executionId 执行 ID
     * @return 执行状态
     */
    Result<ProviderExecutionResultResponse> getExecutionStatus(
            String executionId);

    /**
     * 取消执行
     *
     * @param executionId 执行 ID
     * @return 取消结果
     */
    Result<Void> cancelExecution(
            String executionId);

    // ==================== LLM Provider API ====================

    /**
     * 根据 ID 获取 LLM Provider 配置
     *
     * @param id LLM Provider ID
     * @return LLM Provider 配置
     */
    Result<LlmProviderResponse> getLlmProviderById(String id);

    /**
     * 获取 LLM Provider 凭证（含解析后的 API Key）
     *
     * @param id LLM Provider ID
     * @return LLM 凭证信息
     */
    Result<LlmCredentialsResponse> getLlmCredentials(String id);

    /**
     * 获取所有启用的 LLM Provider
     *
     * @return LLM Provider 列表
     */
    Result<List<LlmProviderResponse>> getEnabledLlmProviders();

    /**
     * 根据提供商获取 LLM Provider 列表
     *
     * @param provider 提供商名称
     * @return LLM Provider 列表
     */
    Result<List<LlmProviderResponse>> getLlmProvidersByProvider(
            String provider);
}
