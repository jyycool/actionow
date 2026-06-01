package com.actionow.ai.client;

import com.actionow.common.core.result.Result;

import java.util.Map;

/**
 * 任务服务 本地客户端
 *
 * @author Actionow
 */
public interface TaskLocalClient {

    /**
     * 创建任务
     */
    Result<Map<String, Object>> createTask(
            String workspaceId,
            CreateTaskRequest request);

    /**
     * 更新任务状态
     */
    Result<Void> updateTaskStatus(UpdateTaskStatusRequest request);

    /**
     * 通知 Task 模块处理 AI 回调结果
     * 用于 CALLBACK 模式：第三方回调到达 AI 模块后，转发给 Task 模块触发完成流程
     *
     * @param taskId  任务 ID
     * @param payload 符合 ProviderExecutionResult 结构的回调数据（由 PluginExecutionResult.toCallbackPayload() 生成）
     */
    Result<Void> notifyTaskCallback(
            String taskId,
            Map<String, Object> payload);

    /**
     * 创建任务请求
     */
    record CreateTaskRequest(
            String name,
            String type,
            String priority,
            Map<String, Object> inputParams,
            String callbackUrl,
            String creatorId
    ) {}

    /**
     * 更新任务状态请求
     */
    record UpdateTaskStatusRequest(
            String taskId,
            String status,
            String errorMessage,
            Map<String, Object> result
    ) {}
}
