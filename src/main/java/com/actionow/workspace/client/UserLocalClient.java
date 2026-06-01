package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 用户服务 本地客户端
 *
 * @author Actionow
 */
public interface UserLocalClient {

    /**
     * 根据用户ID获取基本信息
     */
    Result<UserBasicInfo> getUserBasicInfo(String userId);

    /**
     * 批量获取用户基本信息
     */
    Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds);
}
