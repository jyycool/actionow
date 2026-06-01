package com.actionow.collab.client;

import com.actionow.common.core.result.Result;
import com.actionow.workspace.dto.WorkspaceMembershipInfo;

/**
 * 工作空间服务 本地客户端
 *
 * @author Actionow
 */
public interface WorkspaceLocalClient {

    Result<WorkspaceMembershipInfo> getMembership(String workspaceId, String userId);
}
