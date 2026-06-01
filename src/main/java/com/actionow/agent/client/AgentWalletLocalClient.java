package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.ConfirmConsumeRequest;
import com.actionow.wallet.dto.FreezeRequest;
import com.actionow.wallet.dto.TransactionResponse;
import com.actionow.wallet.dto.UnfreezeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<TransactionResponse> freeze(String workspaceId, FreezeRequest request) {
        request.setWorkspaceId(workspaceId);
        return walletInternalController.freeze(request);
    }

    @Override
    public Result<Void> confirmConsume(String workspaceId, ConfirmConsumeRequest request) {
        request.setWorkspaceId(workspaceId);
        Result<TransactionResponse> result = walletInternalController.confirmConsume(request);
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "钱包操作失败");
        }
        return Result.success(null, result.getMessage());
    }

    @Override
    public Result<Void> unfreeze(String workspaceId, UnfreezeRequest request) {
        request.setWorkspaceId(workspaceId);
        Result<TransactionResponse> result = walletInternalController.unfreeze(request);
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "钱包操作失败");
        }
        return Result.success(null, result.getMessage());
    }

    @Override
    public Result<Boolean> checkQuota(String workspaceId, String userId, long amount) {
        return walletInternalController.checkQuota(workspaceId, userId, amount);
    }

    @Override
    public Result<Boolean> useQuota(String workspaceId, String userId, long amount) {
        return walletInternalController.useQuota(workspaceId, userId, amount);
    }

    @Override
    public Result<Boolean> refundQuota(String workspaceId, String userId, long amount) {
        return walletInternalController.refundQuota(workspaceId, userId, amount);
    }
}
