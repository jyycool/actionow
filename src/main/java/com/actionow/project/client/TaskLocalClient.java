package com.actionow.project.client;

import com.actionow.common.core.result.Result;

import java.util.Map;

/**
 * 任务服务 本地客户端
 * 用于灵感模块提交 AI 生成任务
 *
 * @author Actionow
 */
public interface TaskLocalClient {

    /**
     * 提交 AI 生成任务
     * 走完整流程：积分冻结 → 任务创建 → MQ 发送 → AI 执行
     */
    Result<Map<String, Object>> submitAiGeneration(
            String workspaceId,
            String userId,
            Map<String, Object> request);
}
