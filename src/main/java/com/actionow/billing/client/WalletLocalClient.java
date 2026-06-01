package com.actionow.billing.client;

import com.actionow.common.core.result.Result;

/**
 * 钱包内部接口客户端
 */
public interface WalletLocalClient {

    /**
     * 通过支付订单执行充值入账
     */
    Result<Object> topup(String workspaceId,
                         WalletTopupRequest request,
                         String operatorId);
}
