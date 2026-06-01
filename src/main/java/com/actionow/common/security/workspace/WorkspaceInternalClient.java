package com.actionow.common.security.workspace;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 工作空间服务 本地客户端
 *
 * @author Actionow
 */
public interface WorkspaceInternalClient {

    /**
     * 验证用户是否是工作空间成员
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @return 成员身份信息
     */
    @GetMapping("/internal/workspace/{workspaceId}/membership")
    Result<WorkspaceMembershipInfo> getMembership(
            @PathVariable("workspaceId") String workspaceId,
            @RequestParam("userId") String userId);

    /**
     * 获取工作空间的租户Schema
     *
     * @param workspaceId 工作空间ID
     * @return 租户Schema
     */
    @GetMapping("/internal/workspace/{workspaceId}/schema")
    Result<String> getTenantSchema(@PathVariable("workspaceId") String workspaceId);

    /**
     * 查询工作空间是否为内部测试 workspace
     *
     * @param workspaceId 工作空间ID
     * @return true=内部测试 workspace
     */
    @GetMapping("/internal/workspace/{workspaceId}/is-internal")
    Result<Boolean> isInternal(@PathVariable("workspaceId") String workspaceId);
}
