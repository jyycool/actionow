package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<WalletBasicInfo> createWallet(String workspaceId) {
        Result<WalletResponse> result = walletInternalController.createWallet(workspaceId);
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "创建钱包失败");
        }
        return Result.success(LocalClientDtoMapper.convert(result.getData(), WalletBasicInfo.class), result.getMessage());
    }

    @Override
    public Result<Void> deleteQuota(String workspaceId, String userId, String operatorId) {
        return walletInternalController.deleteQuota(workspaceId, userId, operatorId);
    }
}
