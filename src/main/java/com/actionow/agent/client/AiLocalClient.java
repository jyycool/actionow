package com.actionow.agent.client;

import com.actionow.ai.dto.AvailableProviderResponse;
import com.actionow.ai.dto.ExecutionStatusResponse;
import com.actionow.ai.dto.ProviderExecutionResultResponse;
import com.actionow.ai.llm.dto.LlmCredentialsResponse;
import com.actionow.ai.llm.dto.LlmProviderResponse;
import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;

import java.util.List;

/**
 * AI 服务 本地客户端
 */
public interface AiLocalClient {

    Result<ProviderExecutionResultResponse> executeProvider(ProviderExecuteRequest request);

    Result<List<AvailableProviderResponse>> getAvailableProviders(String providerType);

    Result<AvailableProviderResponse> getProviderDetail(String providerId);

    Result<ExecutionStatusResponse> getExecutionStatus(String executionId);

    Result<Void> cancelExecution(String executionId);

    Result<LlmProviderResponse> getLlmProviderById(String id);

    Result<LlmCredentialsResponse> getLlmCredentials(String id);

    Result<List<LlmProviderResponse>> getEnabledLlmProviders();

    Result<List<LlmProviderResponse>> getLlmProvidersByProvider(String provider);
}
