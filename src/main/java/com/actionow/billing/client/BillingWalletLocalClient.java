package com.actionow.billing.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.wallet.controller.WalletInternalController;
import com.actionow.wallet.dto.TopupRequest;
import com.actionow.wallet.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillingWalletLocalClient implements WalletLocalClient {

    private final WalletInternalController walletInternalController;

    @Override
    public Result<Object> topup(String workspaceId, WalletTopupRequest request, String operatorId) {
        Result<TransactionResponse> result = walletInternalController.topup(
                workspaceId,
                LocalClientDtoMapper.convert(request, TopupRequest.class),
                operatorId
        );
        if (result == null || !result.isSuccess()) {
            return Result.fail(result != null ? result.getMessage() : "钱包充值失败");
        }
        return Result.success(result.getData(), result.getMessage());
    }
}
