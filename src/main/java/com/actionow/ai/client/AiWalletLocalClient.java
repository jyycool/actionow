package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.ConfirmConsumeRequest;
import com.actionow.wallet.dto.FreezeRequest;
import com.actionow.wallet.dto.TransactionResponse;
import com.actionow.wallet.dto.UnfreezeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * AI 模块访问钱包服务的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class AiWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<TransactionResponse> freeze(FreezeRequest request) {
        return walletInternalController.freeze(request);
    }

    @Override
    public Result<TransactionResponse> unfreeze(UnfreezeRequest request) {
        return walletInternalController.unfreeze(request);
    }

    @Override
    public Result<TransactionResponse> confirmConsume(ConfirmConsumeRequest request) {
        return walletInternalController.confirmConsume(request);
    }
}
