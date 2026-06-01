package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;
import com.actionow.user.dto.response.UserBasicInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户服务 本地客户端
 *
 * @author Actionow
 */
public interface UserLocalClient {

    Result<UserBasicInfo> getUserBasicInfo(String userId);

    Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds);
}
