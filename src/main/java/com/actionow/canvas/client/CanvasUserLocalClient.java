package com.actionow.canvas.client;

import com.actionow.canvas.dto.TokenValidateRequest;
import com.actionow.canvas.dto.TokenValidateResponse;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.user.controller.InternalUserController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Canvas 模块访问 User 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class CanvasUserLocalClient implements UserLocalClient {

    private final ObjectProvider<InternalUserController> internalUserControllerProvider;

    private InternalUserController internalUserController() {
        return internalUserControllerProvider.getObject();
    }

    @Override
    public Result<TokenValidateResponse> validateToken(TokenValidateRequest request) {
        com.actionow.user.dto.request.TokenValidateRequest userRequest =
                LocalClientDtoMapper.convert(request, com.actionow.user.dto.request.TokenValidateRequest.class);
        Result<com.actionow.user.dto.response.TokenValidateResponse> source = internalUserController().validateToken(userRequest);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "User 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), TokenValidateResponse.class), source.getMessage());
    }
}
