package com.actionow.collab.client;

import com.actionow.collab.dto.TokenValidateRequest;
import com.actionow.collab.dto.TokenValidateResponse;
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

    Result<TokenValidateResponse> validateToken(TokenValidateRequest request);

    Result<Map<String, UserBasicInfo>> batchGetUserBasicInfo(List<String> userIds);
}
