package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.user.controller.InternalUserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Workspace 模块访问 User 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class WorkspaceUserLocalClient implements UserLocalClient {

    private final InternalUserController internalUserController;

    @Override
    public Result<UserBasicInfo> getUserBasicInfo(String userId) {
        Result<com.actionow.user.dto.response.UserBasicInfo> source = internalUserController.getUserBasicInfo(userId);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "User 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), UserBasicInfo.class), source.getMessage());
    }

    @Override
    public Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds) {
        Result<Map<String, com.actionow.user.dto.response.UserBasicInfo>> source = internalUserController.batchGetUserBasicInfo(userIds);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "User 本地调用失败");
        }
        Map<String, UserBasicInfo> converted = source.getData() == null
                ? Map.of()
                : source.getData().entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> LocalClientDtoMapper.convert(entry.getValue(), UserBasicInfo.class)
        ));
        return Result.success(converted, source.getMessage());
    }
}
