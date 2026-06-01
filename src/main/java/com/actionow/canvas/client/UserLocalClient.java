package com.actionow.canvas.client;

import com.actionow.canvas.dto.TokenValidateRequest;
import com.actionow.canvas.dto.TokenValidateResponse;
import com.actionow.common.core.result.Result;

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
}
