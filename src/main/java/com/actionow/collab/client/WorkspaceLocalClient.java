package com.actionow.collab.client;

import com.actionow.common.core.result.Result;

/**
 * 工作空间服务 本地客户端
 * 用于 WebSocket 握手阶段验证用户是否为 workspace 成员
 *
 * @author Actionow
 */
public interface WorkspaceLocalClient {

    /**
     * 验证用户是否是工作空间成员
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @return 成员身份信息
     */
    Result<WorkspaceMembershipInfo> getMembership(
            String workspaceId,
            String userId);
}
