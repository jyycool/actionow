package com.actionow.ai.client;

import com.actionow.common.core.result.Result;

import java.util.Map;

/**
 * 钱包服务 本地客户端
 *
 * @author Actionow
 */
public interface WalletLocalClient {

    /**
     * 冻结积分
     */
    Result<Map<String, Object>> freeze(
            String workspaceId,
            FreezeRequest request);

    /**
     * 解冻积分
     */
    Result<Void> unfreeze(
            String workspaceId,
            UnfreezeRequest request);

    /**
     * 确认消费
     */
    Result<Void> confirmConsume(
            String workspaceId,
            ConfirmConsumeRequest request);

    /**
     * 冻结请求
     */
    record FreezeRequest(
            Long amount,
            String scene,
            String businessId,
            String remark
    ) {}

    /**
     * 解冻请求
     */
    record UnfreezeRequest(
            String transactionId,
            String businessId,
            String remark
    ) {}

    /**
     * 确认消费请求
     */
    record ConfirmConsumeRequest(
            String transactionId,
            String businessId,
            Long actualAmount,
            String remark
    ) {}
}
