package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.task.controller.TaskInternalController;
import com.actionow.task.dto.CreateTaskRequest;
import com.actionow.task.dto.ProviderExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * AI 模块访问 Task 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class AiTaskLocalClient implements TaskLocalClient {

    private final ObjectProvider<TaskInternalController> taskInternalControllerProvider;

    private TaskInternalController taskInternalController() {
        return taskInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> createTask(String workspaceId, CreateTaskRequest request) {
        return taskInternalController().createTask(workspaceId, request);
    }

    @Override
    public Result<Void> updateTaskStatus(TaskInternalController.UpdateStatusRequest request) {
        return taskInternalController().updateTaskStatus(request);
    }

    @Override
    public Result<Void> notifyTaskCallback(String taskId, Map<String, Object> payload) {
        ProviderExecutionResult result = LocalClientDtoMapper.convert(payload, ProviderExecutionResult.class);
        return taskInternalController().handleTaskCallback(taskId, result);
    }
}
