package com.actionow.collab.client;

import com.actionow.collab.dto.TokenValidateRequest;
import com.actionow.collab.dto.TokenValidateResponse;
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
     * 验证Token并获取用户信息
     *
     * @param request Token验证请求
     * @return Token验证结果，包含用户信息
     */
    Result<TokenValidateResponse> validateToken(TokenValidateRequest request);

    /**
     * 批量获取用户基本信息
     *
     * @param userIds 用户ID列表
     * @return 用户ID到基本信息的映射
     */
    Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds);
}
