package com.actionow.collab.client;

import com.actionow.common.core.result.Result;
import com.actionow.workspace.controller.WorkspaceInternalController;
import com.actionow.workspace.dto.WorkspaceMembershipInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Collab 模块访问 Workspace 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class CollabWorkspaceLocalClient implements WorkspaceLocalClient {

    private final WorkspaceInternalController workspaceInternalController;

    @Override
    public Result<WorkspaceMembershipInfo> getMembership(String workspaceId, String userId) {
        return workspaceInternalController.getMembership(workspaceId, userId);
    }
}
