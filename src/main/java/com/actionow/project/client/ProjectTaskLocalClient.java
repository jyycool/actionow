package com.actionow.project.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.task.controller.TaskInternalController;
import com.actionow.task.dto.SubmitGenerationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Project 模块访问 Task 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class ProjectTaskLocalClient implements TaskLocalClient {

    private final ObjectProvider<TaskInternalController> taskInternalControllerProvider;

    private TaskInternalController taskInternalController() {
        return taskInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> submitAiGeneration(String workspaceId, String userId,
                                                          Map<String, Object> request) {
        SubmitGenerationRequest req = LocalClientDtoMapper.convert(request, SubmitGenerationRequest.class);
        return taskInternalController().submitAiGeneration(workspaceId, userId, req);
    }
}
