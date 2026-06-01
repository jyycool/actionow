package com.actionow.billing.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.dto.TopupRequest;
import com.actionow.wallet.dto.TransactionResponse;

/**
 * 钱包内部接口客户端
 */
public interface WalletLocalClient {

    /**
     * 通过支付订单执行充值入账
     */
    Result<TransactionResponse> topup(String workspaceId,
                                      TopupRequest request,
                                      String operatorId);
}
