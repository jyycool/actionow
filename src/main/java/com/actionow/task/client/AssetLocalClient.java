package com.actionow.task.client;

import com.actionow.common.core.result.Result;
import com.actionow.task.dto.AssetInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 素材服务 本地客户端
 * Task 服务调用 Project 服务更新素材状态
 * 注意: Asset CRUD 已迁移到 actionow-project
 *
 * @author Actionow
 */
public interface AssetLocalClient {

    /**
     * 创建素材
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param request     创建请求
     * @return 创建的素材信息
     */
    Result<Map<String, Object>> createAsset(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    /**
     * 创建实体素材关联
     *
     * @param workspaceId 工作空间 ID
     * @param userId      用户 ID
     * @param request     关联请求
     * @return 创建的关联信息
     */
    Result<Map<String, Object>> createEntityAssetRelation(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    /**
     * 更新素材生成状态
     *
     * @param assetId 素材 ID
     * @param status  生成状态
     * @return 操作结果
     */
    Result<Void> updateGenerationStatus(
            String assetId,
            String status);

    /**
     * 更新素材文件信息
     *
     * @param assetId  素材 ID
     * @param fileInfo 文件信息
     * @return 操作结果
     */
    Result<Void> updateFileInfo(
            String assetId,
            Map<String, Object> fileInfo);

    /**
     * 更新素材扩展信息
     *
     * @param workspaceId 工作空间 ID
     * @param assetId     素材 ID
     * @param extraInfo   扩展信息
     * @return 操作结果
     */
    Result<Void> updateAssetExtraInfo(
            String workspaceId,
            String assetId,
            Map<String, Object> extraInfo);

    /**
     * 获取素材信息
     *
     * @param assetId 素材 ID
     * @return 素材信息
     */
    Result<AssetInfoResponse> getAssetInfo(String assetId);

    /**
     * 获取素材信息（带工作空间）
     *
     * @param workspaceId 工作空间 ID
     * @param assetId     素材 ID
     * @return 素材信息
     */
    Result<Map<String, Object>> getAsset(
            String workspaceId,
            String assetId);

    /**
     * 批量获取素材信息
     *
     * @param assetIds 素材 ID 列表
     * @return 素材信息列表
     */
    Result<List<AssetInfoResponse>> batchGetAssets(List<String> assetIds);

    /**
     * 根据任务ID查询素材
     *
     * @param taskId 任务 ID
     * @return 素材信息
     */
    Result<AssetInfoResponse> getByTaskId(String taskId);

    // ==================== 实体素材查询（条件跳过） ====================

    /**
     * 查询实体关联的素材列表
     * 用于批量作业条件跳过检查：如果实体已有 asset 则跳过生成
     *
     * @param workspaceId 工作空间ID
     * @param entityType  实体类型 (CHARACTER, SCENE, PROP, STYLE, STORYBOARD)
     * @param entityId    实体ID
     * @return 关联的素材列表
     */
    Result<List<Map<String, Object>>> getEntityAssets(
            String workspaceId,
            String entityType,
            String entityId);

    /**
     * 批量检查实体是否已有素材
     * 用于批量作业条件跳过的批量预检查，避免 N+1 Local 调用
     *
     * @param workspaceId 工作空间ID
     * @param queries     查询列表，每项包含 entityType 和 entityId
     * @return Map: "entityType:entityId" → true/false
     */
    Result<Map<String, Boolean>> batchCheckEntityAssets(
            String workspaceId,
            List<Map<String, String>> queries);
}
