package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.dto.ConfirmConsumeRequest;
import com.actionow.wallet.dto.FreezeRequest;
import com.actionow.wallet.dto.TransactionResponse;
import com.actionow.wallet.dto.UnfreezeRequest;

/**
 * 钱包服务 本地客户端
 */
public interface WalletLocalClient {

    Result<TransactionResponse> freeze(String workspaceId, FreezeRequest request);

    Result<Void> confirmConsume(String workspaceId, ConfirmConsumeRequest request);

    Result<Void> unfreeze(String workspaceId, UnfreezeRequest request);

    Result<Boolean> checkQuota(String workspaceId, String userId, long amount);

    Result<Boolean> useQuota(String workspaceId, String userId, long amount);

    Result<Boolean> refundQuota(String workspaceId, String userId, long amount);
}
