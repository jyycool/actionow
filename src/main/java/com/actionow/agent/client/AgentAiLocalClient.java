package com.actionow.agent.client;

import com.actionow.ai.controller.AiInternalController;
import com.actionow.ai.dto.AvailableProviderResponse;
import com.actionow.ai.dto.ExecutionStatusResponse;
import com.actionow.ai.dto.ProviderExecutionResultResponse;
import com.actionow.ai.llm.dto.LlmCredentialsResponse;
import com.actionow.ai.llm.dto.LlmProviderResponse;
import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgentAiLocalClient implements AiLocalClient {

    private final AiInternalController aiInternalController;

    @Override
    public Result<ProviderExecutionResultResponse> executeProvider(ProviderExecuteRequest request) {
        return aiInternalController.executeProvider(request);
    }

    @Override
    public Result<List<AvailableProviderResponse>> getAvailableProviders(String providerType) {
        return aiInternalController.getAvailableProviders(providerType);
    }

    @Override
    public Result<AvailableProviderResponse> getProviderDetail(String providerId) {
        return aiInternalController.getProviderDetail(providerId);
    }

    @Override
    public Result<ExecutionStatusResponse> getExecutionStatus(String executionId) {
        return aiInternalController.getExecutionStatus(executionId);
    }

    @Override
    public Result<Void> cancelExecution(String executionId) {
        return aiInternalController.cancelExecution(executionId, null, null, null);
    }

    @Override
    public Result<LlmProviderResponse> getLlmProviderById(String id) {
        return aiInternalController.getLlmProviderById(id);
    }

    @Override
    public Result<LlmCredentialsResponse> getLlmCredentials(String id) {
        return aiInternalController.getLlmCredentials(id);
    }

    @Override
    public Result<List<LlmProviderResponse>> getEnabledLlmProviders() {
        return aiInternalController.getEnabledLlmProviders();
    }

    @Override
    public Result<List<LlmProviderResponse>> getLlmProvidersByProvider(String provider) {
        return aiInternalController.getLlmProvidersByProvider(provider);
    }
}
