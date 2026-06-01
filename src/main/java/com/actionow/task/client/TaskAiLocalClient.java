package com.actionow.task.client;

import com.actionow.ai.controller.AiInternalController;
import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.task.dto.AvailableProviderResponse;
import com.actionow.task.dto.ExecutionStatusResponse;
import com.actionow.task.dto.ProviderExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Task 模块访问 AI 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class TaskAiLocalClient implements AiLocalClient {

    private final AiInternalController aiInternalController;

    @Override
    public Result<ProviderExecutionResult> executeProvider(ProviderExecuteRequest request) {
        return convert(aiInternalController.executeProvider(request), ProviderExecutionResult.class);
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
    public Result<Map<String, Object>> estimateCost(String providerId, Map<String, Object> params) {
        return aiInternalController.estimateCost(providerId, params);
    }

    @Override
    public Result<ExecutionStatusResponse> getExecutionStatus(String executionId) {
        return convert(aiInternalController.getExecutionStatus(executionId), ExecutionStatusResponse.class);
    }

    @Override
    public Result<ProviderExecutionResult> getExecutionResult(String executionId, long timeout) {
        return convert(aiInternalController.getExecutionResult(executionId, timeout), ProviderExecutionResult.class);
    }

    @Override
    public Result<Void> cancelExecution(String executionId, String pluginId, String externalTaskId, String userId) {
        return aiInternalController.cancelExecution(executionId, pluginId, externalTaskId, userId);
    }

    private <T> Result<T> convert(Result<?> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "AI 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), targetType), source.getMessage());
    }

    private <T> Result<List<T>> convertList(Result<? extends List<?>> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "AI 本地调用失败");
        }
        List<T> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, targetType))
                .toList();
        return Result.success(converted, source.getMessage());
    }
}
