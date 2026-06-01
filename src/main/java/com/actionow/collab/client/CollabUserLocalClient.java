package com.actionow.collab.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.collab.dto.TokenValidateRequest;
import com.actionow.collab.dto.TokenValidateResponse;
import com.actionow.user.controller.InternalUserController;
import com.actionow.user.dto.response.UserBasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Collab 模块访问 User 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class CollabUserLocalClient implements UserLocalClient {

    private final InternalUserController internalUserController;

    @Override
    public Result<TokenValidateResponse> validateToken(TokenValidateRequest request) {
        com.actionow.user.dto.request.TokenValidateRequest userRequest =
                LocalClientDtoMapper.convert(request, com.actionow.user.dto.request.TokenValidateRequest.class);
        Result<com.actionow.user.dto.response.TokenValidateResponse> source =
                internalUserController.validateToken(userRequest);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "User 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), TokenValidateResponse.class),
                source.getMessage());
    }

    @Override
    public Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds) {
        return internalUserController.batchGetUserBasicInfo(userIds);
    }
}
