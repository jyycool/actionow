package com.actionow.billing.client;

import com.actionow.common.core.result.Result;

/**
 * 工作空间内部接口客户端
 */
public interface WorkspaceLocalClient {

    /**
     * 内部同步工作空间订阅计划
     */
    Result<Void> updatePlanInternal(String workspaceId,
                                    String planType,
                                    String operatorId);
}
