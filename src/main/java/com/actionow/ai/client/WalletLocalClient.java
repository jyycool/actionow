package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.dto.ConfirmConsumeRequest;
import com.actionow.wallet.dto.FreezeRequest;
import com.actionow.wallet.dto.TransactionResponse;
import com.actionow.wallet.dto.UnfreezeRequest;

/**
 * 钱包服务 本地客户端
 *
 * @author Actionow
 */
public interface WalletLocalClient {

    /**
     * 冻结积分
     */
    Result<TransactionResponse> freeze(FreezeRequest request);

    /**
     * 解冻积分
     */
    Result<TransactionResponse> unfreeze(UnfreezeRequest request);

    /**
     * 确认消费
     */
    Result<TransactionResponse> confirmConsume(ConfirmConsumeRequest request);
}
