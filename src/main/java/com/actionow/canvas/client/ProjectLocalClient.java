package com.actionow.canvas.client;

import com.actionow.canvas.dto.BatchEntityCreateRequest;
import com.actionow.canvas.dto.BatchEntityCreateResponse;
import com.actionow.canvas.dto.BatchEntityUpdateRequest;
import com.actionow.canvas.dto.BatchEntityUpdateResponse;
import com.actionow.canvas.dto.CanvasEntityCreateRequest;
import com.actionow.canvas.dto.CanvasEntityCreateResponse;
import com.actionow.canvas.dto.CanvasEntityUpdateRequest;
import com.actionow.canvas.dto.CanvasEntityUpdateResponse;
import com.actionow.canvas.dto.CreateEntityAssetRelationRequest;
import com.actionow.canvas.dto.EntityAssetRelationResponse;
import com.actionow.canvas.dto.EntityInfo;
import com.actionow.common.core.result.Result;

import java.util.List;

/**
 * Project 服务 本地客户端
 * Canvas 服务调用 Project 服务获取实体数据
 *
 * @author Actionow
 */
public interface ProjectLocalClient {

    // ==================== 实体创建接口 ====================

    /**
     * 从 Canvas 创建实体
     * 当在 Canvas 中创建节点时，调用此接口同步创建业务实体
     *
     * @param request 创建请求
     * @return 创建的实体信息
     */
    Result<CanvasEntityCreateResponse> createEntity(CanvasEntityCreateRequest request);

    /**
     * 批量创建实体
     * Canvas 批量创建节点时调用，提高吞吐量
     *
     * @param request 批量创建请求
     * @return 批量创建结果
     */
    Result<BatchEntityCreateResponse> batchCreateEntities(BatchEntityCreateRequest request);

    /**
     * 从 Canvas 更新实体
     * 当在 Canvas 中更新节点时，调用此接口同步更新业务实体
     *
     * @param request 更新请求
     * @return 更新后的实体信息
     */
    Result<CanvasEntityUpdateResponse> updateEntity(CanvasEntityUpdateRequest request);

    /**
     * 批量更新实体
     * Canvas 批量更新节点时调用，提高吞吐量
     *
     * @param request 批量更新请求
     * @return 批量更新结果
     */
    Result<BatchEntityUpdateResponse> batchUpdateEntities(BatchEntityUpdateRequest request);

    /**
     * 从 Canvas 删除实体
     * 当在 Canvas 中删除节点时，调用此接口同步删除业务实体
     *
     * @param entityType 实体类型
     * @param entityId   实体ID
     * @return 删除结果
     */
    Result<Void> deleteEntity(String entityType,
                              String entityId);

    // ==================== 批量查询接口 ====================

    /**
     * 批量获取剧本信息
     *
     * @param ids 剧本ID列表
     * @return 剧本信息列表
     */
    Result<List<EntityInfo>> batchGetScripts(List<String> ids);

    /**
     * 批量获取章节信息
     *
     * @param ids 章节ID列表
     * @return 章节信息列表
     */
    Result<List<EntityInfo>> batchGetEpisodes(List<String> ids);

    /**
     * 批量获取分镜信息
     *
     * @param ids 分镜ID列表
     * @return 分镜信息列表
     */
    Result<List<EntityInfo>> batchGetStoryboards(List<String> ids);

    /**
     * 批量获取角色信息
     *
     * @param ids 角色ID列表
     * @return 角色信息列表
     */
    Result<List<EntityInfo>> batchGetCharacters(List<String> ids);

    /**
     * 批量获取场景信息
     *
     * @param ids 场景ID列表
     * @return 场景信息列表
     */
    Result<List<EntityInfo>> batchGetScenes(List<String> ids);

    /**
     * 批量获取道具信息
     *
     * @param ids 道具ID列表
     * @return 道具信息列表
     */
    Result<List<EntityInfo>> batchGetProps(List<String> ids);

    /**
     * 批量获取素材信息
     *
     * @param ids 素材ID列表
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> batchGetAssets(List<String> ids);

    /**
     * 批量获取风格信息
     *
     * @param ids 风格ID列表
     * @return 风格信息列表
     */
    Result<List<EntityInfo>> batchGetStyles(List<String> ids);

    // ==================== 容器内容查询接口 ====================

    /**
     * 获取剧本下的所有实体
     *
     * @param scriptId    剧本ID
     * @param entityTypes 实体类型列表 (EPISODE, CHARACTER, SCENE, PROP, ASSET)
     * @return 实体信息列表
     */
    Result<List<EntityInfo>> getEntitiesByScript(
            String scriptId,
            List<String> entityTypes);

    /**
     * 获取章节下的所有实体
     *
     * @param episodeId   章节ID
     * @param entityTypes 实体类型列表 (STORYBOARD, CHARACTER, SCENE, PROP, ASSET)
     * @return 实体信息列表
     */
    Result<List<EntityInfo>> getEntitiesByEpisode(
            String episodeId,
            List<String> entityTypes);

    // ==================== 素材关联查询接口 ====================

    /**
     * 获取角色关联的素材
     *
     * @param characterId 角色ID
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> getAssetsByCharacter(String characterId);

    /**
     * 获取场景关联的素材
     *
     * @param sceneId 场景ID
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> getAssetsByScene(String sceneId);

    /**
     * 获取道具关联的素材
     *
     * @param propId 道具ID
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> getAssetsByProp(String propId);

    /**
     * 获取分镜关联的素材
     *
     * @param storyboardId 分镜ID
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> getAssetsByStoryboard(String storyboardId);

    /**
     * 根据实体类型和ID获取关联的素材
     *
     * @param entityType 实体类型
     * @param entityId   实体ID
     * @return 素材信息列表
     */
    Result<List<EntityInfo>> getRelatedAssets(
            String entityType,
            String entityId);

    // ==================== 实体素材关联管理接口 ====================

    /**
     * 创建实体-素材关联
     * 当在 Canvas 中创建 ASSET 类型边时，调用此接口同步创建关联记录
     *
     * @param request 创建关联请求
     * @return 创建的关联信息
     */
    Result<EntityAssetRelationResponse> createEntityAssetRelation(
            CreateEntityAssetRelationRequest request);
}
