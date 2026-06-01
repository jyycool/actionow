package com.actionow.system.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.TopupRequest;
import com.actionow.wallet.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<TransactionResponse> topup(String workspaceId, TopupRequest request, String operatorId) {
        return walletInternalController.topup(workspaceId, request, operatorId);
    }
}
