package com.actionow.system.client;

import com.actionow.common.core.result.Result;

/**
 * 钱包内部接口客户端（system 模块用）
 */
public interface WalletLocalClient {

    Result<Object> topup(String workspaceId,
                         WalletTopupRequest request,
                         String operatorId);
}
