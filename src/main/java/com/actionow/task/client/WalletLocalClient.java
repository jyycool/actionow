package com.actionow.task.client;

import com.actionow.common.core.result.Result;
import com.actionow.task.dto.FreezeRequest;
import com.actionow.task.dto.FreezeResponse;
import com.actionow.task.dto.ConfirmConsumeRequest;
import com.actionow.task.dto.UnfreezeRequest;

/**
 * 钱包服务 本地客户端
 * Task 服务调用 Wallet 服务管理积分
 *
 * @author Actionow
 */
public interface WalletLocalClient {

    /**
     * 冻结积分
     *
     * @param workspaceId 工作空间 ID
     * @param request 冻结请求
     * @return 冻结结果（含 transactionId）
     */
    Result<FreezeResponse> freeze(
            String workspaceId,
            FreezeRequest request);

    /**
     * 确认消费（扣除冻结的积分）
     *
     * @param workspaceId 工作空间 ID
     * @param request 确认消费请求
     * @return 操作结果
     */
    Result<Void> confirmConsume(
            String workspaceId,
            ConfirmConsumeRequest request);

    /**
     * 解冻积分（取消冻结）
     *
     * @param workspaceId 工作空间 ID
     * @param request 解冻请求
     * @return 操作结果
     */
    Result<Void> unfreeze(
            String workspaceId,
            UnfreezeRequest request);

    // ==================== 成员配额相关 ====================

    /**
     * 检查成员配额是否足够
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param amount      预计使用的积分数量
     * @return 配额是否足够
     */
    Result<Boolean> checkQuota(
            String workspaceId,
            String userId,
            long amount);

    /**
     * 使用成员配额
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param amount      使用的积分数量
     * @return 是否成功
     */
    Result<Boolean> useQuota(
            String workspaceId,
            String userId,
            long amount);

    /**
     * 退还成员配额
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param amount      退还的积分数量
     * @return 是否成功
     */
    Result<Boolean> refundQuota(
            String workspaceId,
            String userId,
            long amount);
}
