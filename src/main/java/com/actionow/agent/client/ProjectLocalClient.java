package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Project 服务 本地客户端
 * 用于调用 actionow-project 模块的 API
 *
 * @author Actionow
 */
public interface ProjectLocalClient {

    // ==================== Script ====================

    Result<Map<String, Object>> createScript(Map<String, Object> request);

    Result<Map<String, Object>> getScript(String scriptId);

    Result<Map<String, Object>> updateScript(String scriptId,
                                              Map<String, Object> request);

    Result<List<Map<String, Object>>> listScripts();

    /**
     * 分页搜索剧本，支持按标题/简介/正文/附加信息模糊搜索
     */
    Result<Map<String, Object>> queryScripts(
            String keyword,
            String status,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    // ==================== Episode ====================

    Result<Map<String, Object>> createEpisode(Map<String, Object> request);

    Result<Map<String, Object>> getEpisode(String episodeId);

    Result<Map<String, Object>> updateEpisode(String episodeId,
                                               Map<String, Object> request);

    Result<List<Map<String, Object>>> listEpisodesByScript(
            String scriptId,
            String keyword,
            Integer limit);

    /** 软删除剧集（deleted=1，可在回收站还原）。 */
    Result<Void> deleteEpisode(String episodeId);

    /**
     * 分页搜索剧集
     */
    Result<Map<String, Object>> queryEpisodes(
            String scriptId,
            String status,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    // ==================== Storyboard ====================

    Result<Map<String, Object>> createStoryboard(Map<String, Object> request);

    Result<Map<String, Object>> getStoryboard(String storyboardId);

    Result<Map<String, Object>> updateStoryboard(String storyboardId,
                                                  Map<String, Object> request);

    Result<List<Map<String, Object>>> listStoryboardsByEpisode(
            String episodeId,
            String keyword,
            Integer limit);

    /** 软删除分镜（deleted=1，可在回收站还原）。 */
    Result<Void> deleteStoryboard(String storyboardId);

    /**
     * 分页搜索分镜
     */
    Result<Map<String, Object>> queryStoryboards(
            String scriptId,
            String episodeId,
            String status,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    /**
     * 获取分镜的所有实体关系
     * 返回角色、场景、道具、对白的关系数据
     */
    Result<Map<String, Object>> getStoryboardRelations(
            String storyboardId);

    // ==================== Character ====================

    Result<Map<String, Object>> createCharacter(Map<String, Object> request);

    Result<Map<String, Object>> getCharacter(String characterId);

    Result<Map<String, Object>> updateCharacter(String characterId,
                                                 Map<String, Object> request);

    Result<List<Map<String, Object>>> listAvailableCharacters(
            String scriptId,
            String keyword,
            Integer limit);

    /** 软删除角色（deleted=1，可在回收站还原）。 */
    Result<Void> deleteCharacter(String characterId);

    /**
     * 分页搜索角色（跨 schema：SYSTEM + WORKSPACE + SCRIPT）
     */
    Result<Map<String, Object>> queryCharacters(
            String scope,
            String scriptId,
            String characterType,
            String gender,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    // ==================== Scene ====================

    Result<Map<String, Object>> createScene(Map<String, Object> request);

    Result<Map<String, Object>> getScene(String sceneId);

    Result<List<Map<String, Object>>> listAvailableScenes(
            String scriptId,
            String keyword,
            Integer limit);

    /**
     * 分页搜索场景（跨 schema：SYSTEM + WORKSPACE + SCRIPT）
     */
    Result<Map<String, Object>> queryScenes(
            String scope,
            String scriptId,
            String sceneType,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    Result<Map<String, Object>> updateScene(String sceneId,
                                             Map<String, Object> request);

    /** 软删除场景（deleted=1，可在回收站还原）。 */
    Result<Void> deleteScene(String sceneId);

    // ==================== Prop ====================

    Result<Map<String, Object>> createProp(Map<String, Object> request);

    Result<Map<String, Object>> getProp(String propId);

    Result<List<Map<String, Object>>> listAvailableProps(
            String scriptId,
            String keyword,
            Integer limit);

    /**
     * 分页搜索道具（跨 schema：SYSTEM + WORKSPACE + SCRIPT）
     */
    Result<Map<String, Object>> queryProps(
            String scope,
            String scriptId,
            String propType,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    Result<Map<String, Object>> updateProp(String propId,
                                            Map<String, Object> request);

    /** 软删除道具（deleted=1，可在回收站还原）。 */
    Result<Void> deleteProp(String propId);

    // ==================== Style ====================

    Result<Map<String, Object>> createStyle(Map<String, Object> request);

    Result<Map<String, Object>> getStyle(String styleId);

    Result<List<Map<String, Object>>> listAvailableStyles(
            String scriptId,
            String keyword,
            Integer limit);

    /**
     * 分页搜索风格（跨 schema：SYSTEM + WORKSPACE + SCRIPT）
     */
    Result<Map<String, Object>> queryStyles(
            String scope,
            String scriptId,
            String keyword,
            Integer pageNum,
            Integer pageSize,
            String orderBy,
            String orderDir);

    Result<Map<String, Object>> updateStyle(String styleId,
                                             Map<String, Object> request);

    /** 软删除风格（deleted=1，可在回收站还原）。 */
    Result<Void> deleteStyle(String styleId);

    // ==================== Batch Create ====================

    // ==================== Asset (Public API) ====================

    /**
     * 分页搜索素材，支持按名称/描述/标签/附加信息模糊搜索
     */
    Result<Map<String, Object>> queryAssets(
            String keyword,
            String scriptId,
            String assetType,
            String source,
            String generationStatus,
            String scope,
            Integer page,
            Integer size);

    /**
     * 更新素材信息（名称/描述/附加信息）
     */
    Result<Map<String, Object>> updateAsset(
            String assetId,
            Map<String, Object> request);

    // ==================== Batch Create (Internal) ====================

    Result<List<Map<String, Object>>> batchCreateEpisodes(
            String workspaceId,
            String userId,
            String scriptId,
            List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateStoryboards(
            String workspaceId,
            String userId,
            String episodeId,
            List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateCharacters(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateScenes(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateProps(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateStyles(
            String workspaceId,
            String userId,
            List<Map<String, Object>> requests);

    // ==================== Unified Batch Query ====================

    /**
     * 统一批量查询多类型实体
     * 适用于获取分镜中引用的所有角色、场景、道具
     *
     * @param request Map 包含: characterIds, sceneIds, propIds, styleIds
     * @return 按类型分组的实体详情
     */
    Result<Map<String, List<Map<String, Object>>>> batchQueryEntities(
            Map<String, List<String>> request);

    // ==================== Entity Relation ====================

    /**
     * 创建实体关系
     */
    Result<Map<String, Object>> createEntityRelation(Map<String, Object> request);

    /**
     * 批量创建实体关系
     */
    Result<List<Map<String, Object>>> batchCreateEntityRelations(List<Map<String, Object>> requests);

    /**
     * 更新实体关系
     */
    Result<Map<String, Object>> updateEntityRelation(
            String relationId,
            Map<String, Object> request);

    /**
     * 删除实体关系
     */
    Result<Void> deleteEntityRelation(String relationId);

    /**
     * 查询源实体的所有关系
     */
    Result<List<Map<String, Object>>> listRelationsBySource(
            String sourceType,
            String sourceId);

    /**
     * 查询源实体指定类型的关系
     */
    Result<List<Map<String, Object>>> listRelationsBySourceAndType(
            String sourceType,
            String sourceId,
            String relationType);

    /**
     * 查询目标实体的入向关系
     */
    Result<List<Map<String, Object>>> listRelationsByTarget(
            String targetType,
            String targetId);

    /**
     * 获取或创建关系（幂等操作）
     * 如果关系已存在则返回现有关系，否则创建新关系
     */
    Result<Map<String, Object>> getOrCreateEntityRelation(Map<String, Object> request);
}
