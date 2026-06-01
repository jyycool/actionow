package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.task.controller.TaskInternalController;
import com.actionow.task.dto.CreateBatchJobRequest;
import com.actionow.task.dto.SubmitGenerationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Agent 模块访问 Task 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class AgentTaskLocalClient implements TaskLocalClient {

    private final ObjectProvider<TaskInternalController> taskInternalControllerProvider;

    private TaskInternalController taskInternalController() {
        return taskInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> submitAiGeneration(String workspaceId, String userId, Map<String, Object> request) {
        SubmitGenerationRequest submitRequest = LocalClientDtoMapper.convert(request, SubmitGenerationRequest.class);
        return taskInternalController().submitAiGeneration(workspaceId, userId, submitRequest);
    }

    @Override
    public Result<Map<String, Object>> getTaskResult(String taskId) {
        return convert(taskInternalController().getTaskResult(taskId));
    }

    @Override
    public Result<Map<String, Object>> getTask(String taskId) {
        return convert(taskInternalController().getTask(taskId));
    }

    @Override
    public Result<Void> cancelTask(String taskId, String userId) {
        return taskInternalController().cancelTaskInternal(taskId, userId);
    }

    @Override
    public Result<Map<String, Object>> createBatchJob(String workspaceId, String userId, Map<String, Object> request) {
        CreateBatchJobRequest createRequest = LocalClientDtoMapper.convert(request, CreateBatchJobRequest.class);
        return convert(taskInternalController().createBatchJob(workspaceId, userId, createRequest));
    }

    @Override
    public Result<Map<String, Object>> getBatchJob(String batchJobId) {
        return convert(taskInternalController().getBatchJob(batchJobId));
    }

    @Override
    public Result<Void> cancelBatchJob(String batchJobId, String userId) {
        return taskInternalController().cancelBatchJob(batchJobId, userId);
    }

    @Override
    public Result<List<Map<String, Object>>> getBatchJobItems(String batchJobId) {
        return taskInternalController().getBatchJobItems(batchJobId);
    }

    @SuppressWarnings("unchecked")
    private Result<Map<String, Object>> convert(Result<?> source) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Task 本地调用失败");
        }
        return Result.success((Map<String, Object>) LocalClientDtoMapper.convert(source.getData(), Map.class), source.getMessage());
    }
}
