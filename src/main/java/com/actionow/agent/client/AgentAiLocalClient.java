package com.actionow.agent.client;

import com.actionow.agent.client.dto.AvailableProviderResponse;
import com.actionow.agent.client.dto.LlmCredentialsResponse;
import com.actionow.agent.client.dto.LlmProviderResponse;
import com.actionow.agent.client.dto.ProviderExecutionResultResponse;
import com.actionow.ai.controller.AiInternalController;
import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgentAiLocalClient implements AiLocalClient {

    private final AiInternalController aiInternalController;

    @Override
    public Result<ProviderExecutionResultResponse> executeProvider(ProviderExecuteRequest request) {
        return convert(aiInternalController.executeProvider(request), ProviderExecutionResultResponse.class);
    }

    @Override
    public Result<List<AvailableProviderResponse>> getAvailableProviders(String providerType) {
        return convertList(aiInternalController.getAvailableProviders(providerType), AvailableProviderResponse.class);
    }

    @Override
    public Result<AvailableProviderResponse> getProviderDetail(String providerId) {
        return convert(aiInternalController.getProviderDetail(providerId), AvailableProviderResponse.class);
    }

    @Override
    public Result<ProviderExecutionResultResponse> getExecutionStatus(String executionId) {
        return convert(aiInternalController.getExecutionStatus(executionId), ProviderExecutionResultResponse.class);
    }

    @Override
    public Result<Void> cancelExecution(String executionId) {
        return aiInternalController.cancelExecution(executionId, null, null, null);
    }

    @Override
    public Result<LlmProviderResponse> getLlmProviderById(String id) {
        return convert(aiInternalController.getLlmProviderById(id), LlmProviderResponse.class);
    }

    @Override
    public Result<LlmCredentialsResponse> getLlmCredentials(String id) {
        return convert(aiInternalController.getLlmCredentials(id), LlmCredentialsResponse.class);
    }

    @Override
    public Result<List<LlmProviderResponse>> getEnabledLlmProviders() {
        return convertList(aiInternalController.getEnabledLlmProviders(), LlmProviderResponse.class);
    }

    @Override
    public Result<List<LlmProviderResponse>> getLlmProvidersByProvider(String provider) {
        return convertList(aiInternalController.getLlmProvidersByProvider(provider), LlmProviderResponse.class);
    }

    private <T> Result<T> convert(Result<?> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "AI 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), targetType), source.getMessage());
    }

    private <T> Result<List<T>> convertList(Result<? extends List<?>> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "AI 本地调用失败");
        }
        List<T> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, targetType))
                .toList();
        return Result.success(converted, source.getMessage());
    }
}
