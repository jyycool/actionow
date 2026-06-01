package com.actionow.agent.client;

import com.actionow.agent.client.dto.AssetDetailResponse;
import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 素材服务内部 本地客户端
 * 用于 Agent 模块创建素材、查询素材和管理实体-素材关联
 *
 * @author Actionow
 */
public interface AssetLocalClient {

    // ==================== 素材管理 ====================

    /**
     * 创建素材
     * Agent 在发起 AI 生成任务前先创建素材记录（状态为 GENERATING）
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @param request     创建请求
     * @return 创建的素材信息
     */
    Result<Map<String, Object>> createAsset(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    /**
     * 获取素材详情
     *
     * @param workspaceId 工作空间ID
     * @param assetId     素材ID
     * @return 素材详细信息（包含预签名URL）
     */
    Result<Map<String, Object>> getAsset(
            String workspaceId,
            String assetId);

    /**
     * 软删除素材（入回收站，deleted=1，可恢复）
     * 不暴露永久删除（{@code /permanent}）和清空回收站（{@code /trash}）入口。
     */
    Result<Void> deleteAsset(
            String workspaceId,
            String userId,
            String assetId);

    /**
     * 批量获取素材信息（Canvas 格式，返回 EntityInfoResponse）
     *
     * @param assetIds 素材ID列表
     * @return 素材信息列表
     */
    Result<List<Map<String, Object>>> batchGetAssets(List<String> assetIds);

    /**
     * 批量获取素材详情（完整 AssetResponse，含 url/mimeType/fileSize 等）
     *
     * @param assetIds 素材ID列表
     * @return 素材详情列表
     */
    Result<List<AssetDetailResponse>> batchGetAssetDetails(List<String> assetIds);

    // ==================== 实体-素材关联查询 ====================

    /**
     * 查询实体关联的素材列表
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
     * 根据关联类型查询实体关联的素材
     *
     * @param workspaceId  工作空间ID
     * @param entityType   实体类型
     * @param entityId     实体ID
     * @param relationType 关联类型 (REFERENCE, OFFICIAL, DRAFT)
     * @return 关联的素材列表
     */
    Result<List<Map<String, Object>>> getEntityAssetsByType(
            String workspaceId,
            String entityType,
            String entityId,
            String relationType);

    // ==================== 实体-素材关联管理 ====================

    /**
     * 创建实体-素材关联
     * 将素材关联到实体（角色、场景、道具、分镜等）
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @param request     关联请求（entityType, entityId, assetId, relationType）
     * @return 创建的关联信息
     */
    Result<Map<String, Object>> createEntityAssetRelation(
            String workspaceId,
            String userId,
            Map<String, Object> request);

    // ==================== 批量操作 ====================

    /**
     * 批量创建素材
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @param requests    素材创建请求列表
     * @return 创建的素材列表
     */
    Result<List<Map<String, Object>>> batchCreateAssets(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    /**
     * 批量创建实体-素材关联
     *
     * @param workspaceId 工作空间ID
     * @param userId      用户ID
     * @param requests    关联请求列表
     * @return 创建的关联列表
     */
    Result<List<Map<String, Object>>> batchCreateEntityAssetRelations(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    // ==================== 素材扩展信息和状态更新 ====================

    /**
     * 更新素材扩展信息
     * 用于存储 AI 生成参数、重试次数、错误信息等
     *
     * @param workspaceId 工作空间ID
     * @param assetId     素材ID
     * @param extraInfo   扩展信息
     * @return 操作结果
     */
    Result<Void> updateAssetExtraInfo(
            String workspaceId,
            String assetId,
            Map<String, Object> extraInfo);

    /**
     * 更新素材生成状态
     *
     * @param workspaceId 工作空间ID
     * @param assetId     素材ID
     * @param status      生成状态 (DRAFT, GENERATING, COMPLETED, FAILED)
     * @return 操作结果
     */
    Result<Void> updateGenerationStatus(
            String workspaceId,
            String assetId,
            String status);
}
