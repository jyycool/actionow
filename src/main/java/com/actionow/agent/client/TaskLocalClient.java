package com.actionow.agent.client;

import com.actionow.common.core.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 任务服务 本地客户端
 * Agent 模块通过 Task 模块提交 AI 生成任务（积分冻结、任务编排、MQ 发送）
 *
 * @author Actionow
 */
public interface TaskLocalClient {

    /**
     * 提交 AI 生成任务
     * 走完整流程：积分冻结 → 任务创建 → MQ 发送 → AI 执行
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param request     生成请求参数
     * @return 包含 taskId, status, providerId, creditCost
     */
    Result<Map<String, Object>> submitAiGeneration(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    /**
     * 获取任务结果（轮询）
     *
     * @param taskId 任务 ID
     * @return 任务详情（含 status, outputResult）
     */
    Result<Map<String, Object>> getTaskResult(String taskId);

    /**
     * 获取任务详情
     *
     * @param taskId 任务 ID
     * @return 任务详情
     */
    Result<Map<String, Object>> getTask(String taskId);

    /**
     * 取消任务（内部调用）
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     * @return 操作结果
     */
    Result<Void> cancelTask(String taskId,
                            String userId);

    // ==================== 批量作业 API ====================

    /**
     * 创建批量作业
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param request     批量作业请求
     * @return 包含 batchJobId, status, totalItems 等
     */
    Result<Map<String, Object>> createBatchJob(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    /**
     * 获取批量作业状态
     *
     * @param batchJobId 批量作业 ID
     * @return 批量作业详情
     */
    Result<Map<String, Object>> getBatchJob(String batchJobId);

    /**
     * 取消批量作业
     *
     * @param batchJobId 批量作业 ID
     * @param userId     用户 ID
     * @return 操作结果
     */
    Result<Void> cancelBatchJob(String batchJobId,
                                 String userId);

    /**
     * 获取批量作业子项列表
     *
     * @param batchJobId 批量作业 ID
     * @return 子项列表
     */
    Result<List<Map<String, Object>>> getBatchJobItems(String batchJobId);
}
