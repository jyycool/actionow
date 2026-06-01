package com.actionow.workspace.client;

import com.actionow.common.core.result.Result;
import com.actionow.wallet.dto.WalletResponse;

/**
 * 钱包服务 本地客户端
 * Workspace 服务在创建工作空间时调用，确保同时创建钱包
 *
 * @author Actionow
 */
public interface WalletLocalClient {

    /**
     * 创建工作空间钱包
     * 在创建工作空间时调用，确保钱包与工作空间同时创建
     *
     * @param workspaceId 工作空间 ID
     * @return 创建结果
     */
    Result<WalletResponse> createWallet(String workspaceId);

    /**
     * 删除成员配额记录
     * 在移除/退出成员时调用，避免配额幽灵数据
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param operatorId  操作人 ID
     * @return 操作结果
     */
    Result<Void> deleteQuota(String workspaceId,
                             String userId,
                             String operatorId);
}
