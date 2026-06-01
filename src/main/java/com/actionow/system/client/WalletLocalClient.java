package com.actionow.system.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.dto.TopupRequest;
import com.actionow.wallet.dto.TransactionResponse;

/**
 * 钱包内部接口客户端（system 模块用）
 */
public interface WalletLocalClient {

    Result<TransactionResponse> topup(String workspaceId,
                                      TopupRequest request,
                                      String operatorId);
}
