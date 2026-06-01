package com.actionow.project.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.common.security.workspace.WorkspaceMembershipInfo;
import com.actionow.workspace.controller.WorkspaceInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Project 模块访问 Workspace 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class ProjectWorkspaceLocalClient implements WorkspaceLocalClient {

    private final ObjectProvider<WorkspaceInternalController> workspaceInternalControllerProvider;

    private WorkspaceInternalController workspaceInternalController() {
        return workspaceInternalControllerProvider.getObject();
    }

    @Override
    public Result<WorkspaceMembershipInfo> getMembership(String workspaceId, String userId) {
        Result<com.actionow.workspace.dto.WorkspaceMembershipInfo> source =
                workspaceInternalController().getMembership(workspaceId, userId);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Workspace 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), WorkspaceMembershipInfo.class), source.getMessage());
    }

    @Override
    public Result<Void> addGuestMember(String workspaceId, String userId, String invitedBy) {
        return workspaceInternalController().addGuestMember(workspaceId, userId, invitedBy);
    }
}
