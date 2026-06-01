package com.actionow.task.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.task.dto.ConfirmConsumeRequest;
import com.actionow.task.dto.FreezeRequest;
import com.actionow.task.dto.FreezeResponse;
import com.actionow.task.dto.UnfreezeRequest;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<FreezeResponse> freeze(String workspaceId, FreezeRequest request) {
        com.actionow.wallet.dto.FreezeRequest target = LocalClientDtoMapper.convert(request, com.actionow.wallet.dto.FreezeRequest.class);
        target.setWorkspaceId(workspaceId);
        Result<TransactionResponse> result = walletInternalController.freeze(target);
        return convertFreezeResponse(result);
    }

    @Override
    public Result<Void> confirmConsume(String workspaceId, ConfirmConsumeRequest request) {
        com.actionow.wallet.dto.ConfirmConsumeRequest target = LocalClientDtoMapper.convert(request, com.actionow.wallet.dto.ConfirmConsumeRequest.class);
        target.setWorkspaceId(workspaceId);
        Result<TransactionResponse> result = walletInternalController.confirmConsume(target);
        return toVoid(result);
    }

    @Override
    public Result<Void> unfreeze(String workspaceId, UnfreezeRequest request) {
        com.actionow.wallet.dto.UnfreezeRequest target = LocalClientDtoMapper.convert(request, com.actionow.wallet.dto.UnfreezeRequest.class);
        target.setWorkspaceId(workspaceId);
        Result<TransactionResponse> result = walletInternalController.unfreeze(target);
        return toVoid(result);
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

    private Result<FreezeResponse> convertFreezeResponse(Result<TransactionResponse> result) {
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "冻结积分失败");
        }
        TransactionResponse transaction = result.getData();
        FreezeResponse response = FreezeResponse.builder()
                .transactionId(transaction != null ? transaction.getId() : null)
                .frozenAmount(transaction != null ? transaction.getAmount() : null)
                .build();
        return Result.success(response, result.getMessage());
    }

    private Result<Void> toVoid(Result<?> result) {
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "钱包操作失败");
        }
        return Result.success(null, result.getMessage());
    }
}
