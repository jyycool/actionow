package com.actionow.project.client;

import com.actionow.common.core.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 用户服务 本地客户端
 * 供其他微服务调用用户服务内部接口
 *
 * @author Actionow
 */
public interface UserLocalClient {

    /**
     * 根据用户ID获取基本信息
     *
     * @param userId 用户ID
     * @return 用户基本信息
     */
    Result<UserBasicInfo> getUserBasicInfo(String userId);

    /**
     * 批量获取用户基本信息
     *
     * @param userIds 用户ID列表
     * @return 用户ID到基本信息的映射
     */
    Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds);
}
