package com.actionow.billing.client;

import com.actionow.common.core.result.Result;
import com.actionow.workspace.controller.WorkspaceInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * Billing 模块访问 Workspace 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class BillingWorkspaceLocalClient implements WorkspaceLocalClient {

    private final ObjectProvider<WorkspaceInternalController> workspaceInternalControllerProvider;

    private WorkspaceInternalController workspaceInternalController() {
        return workspaceInternalControllerProvider.getObject();
    }

    @Override
    public Result<Void> updatePlanInternal(String workspaceId, String planType, String operatorId) {
        return workspaceInternalController().updatePlanInternal(workspaceId, planType, operatorId);
    }
}
