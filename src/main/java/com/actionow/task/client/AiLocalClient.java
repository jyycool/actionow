package com.actionow.task.client;

import com.actionow.common.api.ai.ProviderExecuteRequest;
import com.actionow.common.core.result.Result;
import com.actionow.task.dto.AvailableProviderResponse;
import com.actionow.task.dto.ExecutionStatusResponse;
import com.actionow.task.dto.ProviderExecutionResult;

import java.util.List;
import java.util.Map;

/**
 * AI 服务 本地客户端
 * Task 服务调用 AI 服务执行模型提供商
 *
 * @author Actionow
 */
public interface AiLocalClient {

    /**
     * 执行模型提供商
     *
     * @param request 执行请求
     * @return 执行结果
     */
    Result<ProviderExecutionResult> executeProvider(ProviderExecuteRequest request);

    /**
     * 获取可用模型提供商列表
     *
     * @param providerType 生成类型（IMAGE/VIDEO/AUDIO/TEXT）
     * @return 提供商列表
     */
    Result<List<AvailableProviderResponse>> getAvailableProviders(
            String providerType);

    /**
     * 获取模型提供商详情（含费用信息）
     *
     * @param providerId 提供商 ID
     * @return 提供商详情
     */
    Result<AvailableProviderResponse> getProviderDetail(
            String providerId);

    /**
     * 预估积分消耗
     * 根据模型提供商的定价规则和用户参数动态计算积分
     *
     * @param providerId 提供商 ID
     * @param params     用户输入参数
     * @return 积分预估结果（含 finalCost, baseCost, discountRate, breakdown 等）
     */
    Result<Map<String, Object>> estimateCost(
            String providerId,
            Map<String, Object> params);

    /**
     * 查询执行状态（非阻塞）
     * 用于 POLLING 模式下查询任务状态
     *
     * @param executionId 执行 ID
     * @return 执行状态
     */
    Result<ExecutionStatusResponse> getExecutionStatus(
            String executionId);

    /**
     * 获取执行结果（阻塞等待）
     * 用于 POLLING 模式下获取最终结果
     *
     * @param executionId 执行 ID
     * @param timeout     超时时间（秒）
     * @return 执行结果
     */
    Result<ProviderExecutionResult> getExecutionResult(
            String executionId,
            long timeout);

    /**
     * 取消执行
     *
     * @param executionId    执行 ID
     * @param pluginId       插件 ID
     * @param externalTaskId 外部任务 ID
     * @param userId         用户 ID
     * @return 取消结果
     */
    Result<Void> cancelExecution(
            String executionId,
            String pluginId,
            String externalTaskId,
            String userId);
}
