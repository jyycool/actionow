package com.actionow.project.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.security.workspace.WorkspaceMembershipInfo;

/**
 * 工作空间服务 本地客户端
 * 供 project 服务调用 workspace 内部接口
 *
 * @author Actionow
 */
public interface WorkspaceLocalClient {

    /**
     * 获取用户在工作空间的成员身份信息
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @return 成员身份信息
     */
    Result<WorkspaceMembershipInfo> getMembership(String workspaceId,
                                                   String userId);

    /**
     * 将用户以 GUEST 角色添加为工作空间成员（供剧本创建者邀请非成员时使用）
     *
     * @param workspaceId 工作空间ID
     * @param userId      被邀请用户ID
     * @param invitedBy   邀请人ID
     * @return 操作结果
     */
    Result<Void> addGuestMember(String workspaceId,
                                String userId,
                                String invitedBy);
}
