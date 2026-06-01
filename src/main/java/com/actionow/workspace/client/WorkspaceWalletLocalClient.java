package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<WalletResponse> createWallet(String workspaceId) {
        return walletInternalController.createWallet(workspaceId);
    }

    @Override
    public Result<Void> deleteQuota(String workspaceId, String userId, String operatorId) {
        return walletInternalController.deleteQuota(workspaceId, userId, operatorId);
    }
}
