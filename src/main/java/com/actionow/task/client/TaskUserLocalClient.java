package com.actionow.task.client;

import com.actionow.common.core.result.Result;
import com.actionow.user.controller.InternalUserController;
import com.actionow.user.dto.response.UserBasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Task 模块访问 User 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class TaskUserLocalClient implements UserLocalClient {

    private final InternalUserController internalUserController;

    @Override
    public Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds) {
        return internalUserController.batchGetUserBasicInfo(userIds);
    }
}
