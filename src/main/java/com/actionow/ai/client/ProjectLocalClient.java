package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Project 服务 本地客户端
 * 用于 AI 服务操作剧本相关实体（Script, Episode, Storyboard, Character, Scene, Prop, Style, Asset）
 *
 * @author Actionow
 */
public interface ProjectLocalClient {

    // ==================== Script 剧本 ====================

    /**
     * 创建剧本
     */
    Result<Map<String, Object>> createScript(Map<String, Object> request);

    /**
     * 批量创建剧本
     */
    Result<List<String>> batchCreateScripts(List<Map<String, Object>> scripts);

    /**
     * 更新剧本
     */
    Result<Void> updateScript(
            String scriptId,
            Map<String, Object> request);

    /**
     * 删除剧本
     */
    Result<Void> deleteScript(String scriptId);

    /**
     * 批量删除剧本
     */
    Result<Void> batchDeleteScripts(List<String> scriptIds);

    /**
     * 获取剧本详情
     */
    Result<Map<String, Object>> getScript(String scriptId);

    /**
     * 获取工作空间下的剧本列表
     */
    Result<List<Map<String, Object>>> listScripts(String workspaceId);

    // ==================== Episode 章节 ====================

    /**
     * 创建章节
     */
    Result<Map<String, Object>> createEpisode(Map<String, Object> request);

    /**
     * 批量创建章节
     */
    Result<List<String>> batchCreateEpisodes(List<Map<String, Object>> episodes);

    /**
     * 更新章节
     */
    Result<Void> updateEpisode(
            String episodeId,
            Map<String, Object> request);

    /**
     * 删除章节
     */
    Result<Void> deleteEpisode(String episodeId);

    /**
     * 批量删除章节
     */
    Result<Void> batchDeleteEpisodes(List<String> episodeIds);

    /**
     * 获取章节详情
     */
    Result<Map<String, Object>> getEpisode(String episodeId);

    /**
     * 获取剧本下的所有章节
     */
    Result<List<Map<String, Object>>> getEpisodesByScript(String scriptId);

    // ==================== Storyboard 分镜 ====================

    /**
     * 创建分镜
     */
    Result<Map<String, Object>> createStoryboard(Map<String, Object> request);

    /**
     * 批量创建分镜
     */
    Result<List<String>> batchCreateStoryboards(List<Map<String, Object>> storyboards);

    /**
     * 更新分镜
     */
    Result<Void> updateStoryboard(
            String storyboardId,
            Map<String, Object> request);

    /**
     * 删除分镜
     */
    Result<Void> deleteStoryboard(String storyboardId);

    /**
     * 批量删除分镜
     */
    Result<Void> batchDeleteStoryboards(List<String> storyboardIds);

    /**
     * 获取分镜详情
     */
    Result<Map<String, Object>> getStoryboard(String storyboardId);

    /**
     * 获取章节下的所有分镜
     */
    Result<List<Map<String, Object>>> getStoryboardsByEpisode(String episodeId);

    // ==================== Character 角色 ====================

    /**
     * 创建角色
     */
    Result<Map<String, Object>> createCharacter(Map<String, Object> request);

    /**
     * 批量创建角色
     */
    Result<List<String>> batchCreateCharacters(List<Map<String, Object>> characters);

    /**
     * 更新角色
     */
    Result<Void> updateCharacter(
            String characterId,
            Map<String, Object> request);

    /**
     * 删除角色
     */
    Result<Void> deleteCharacter(String characterId);

    /**
     * 批量删除角色
     */
    Result<Void> batchDeleteCharacters(List<String> characterIds);

    /**
     * 获取角色详情
     */
    Result<Map<String, Object>> getCharacter(String characterId);

    /**
     * 获取剧本下的所有角色
     */
    Result<List<Map<String, Object>>> getCharactersByScript(String scriptId);

    // ==================== Scene 场景 ====================

    /**
     * 创建场景
     */
    Result<Map<String, Object>> createScene(Map<String, Object> request);

    /**
     * 批量创建场景
     */
    Result<List<String>> batchCreateScenes(List<Map<String, Object>> scenes);

    /**
     * 更新场景
     */
    Result<Void> updateScene(
            String sceneId,
            Map<String, Object> request);

    /**
     * 删除场景
     */
    Result<Void> deleteScene(String sceneId);

    /**
     * 批量删除场景
     */
    Result<Void> batchDeleteScenes(List<String> sceneIds);

    /**
     * 获取场景详情
     */
    Result<Map<String, Object>> getScene(String sceneId);

    /**
     * 获取剧本下的所有场景
     */
    Result<List<Map<String, Object>>> getScenesByScript(String scriptId);

    // ==================== Prop 道具 ====================

    /**
     * 创建道具
     */
    Result<Map<String, Object>> createProp(Map<String, Object> request);

    /**
     * 批量创建道具
     */
    Result<List<String>> batchCreateProps(List<Map<String, Object>> props);

    /**
     * 更新道具
     */
    Result<Void> updateProp(
            String propId,
            Map<String, Object> request);

    /**
     * 删除道具
     */
    Result<Void> deleteProp(String propId);

    /**
     * 批量删除道具
     */
    Result<Void> batchDeleteProps(List<String> propIds);

    /**
     * 获取道具详情
     */
    Result<Map<String, Object>> getProp(String propId);

    /**
     * 获取剧本下的所有道具
     */
    Result<List<Map<String, Object>>> getPropsByScript(String scriptId);

    // ==================== Style 风格 ====================

    /**
     * 创建风格
     */
    Result<Map<String, Object>> createStyle(Map<String, Object> request);

    /**
     * 批量创建风格
     */
    Result<List<String>> batchCreateStyles(List<Map<String, Object>> styles);

    /**
     * 更新风格
     */
    Result<Void> updateStyle(
            String styleId,
            Map<String, Object> request);

    /**
     * 删除风格
     */
    Result<Void> deleteStyle(String styleId);

    /**
     * 批量删除风格
     */
    Result<Void> batchDeleteStyles(List<String> styleIds);

    /**
     * 获取风格详情
     */
    Result<Map<String, Object>> getStyle(String styleId);

    /**
     * 获取剧本下的所有风格
     */
    Result<List<Map<String, Object>>> getStylesByScript(String scriptId);

    // ==================== Asset 素材 ====================

    /**
     * 创建素材
     */
    Result<Map<String, Object>> createAsset(Map<String, Object> request);

    /**
     * 批量创建素材
     */
    Result<List<String>> batchCreateAssets(List<Map<String, Object>> assets);

    /**
     * 更新素材
     */
    Result<Void> updateAsset(
            String assetId,
            Map<String, Object> request);

    /**
     * 删除素材
     */
    Result<Void> deleteAsset(String assetId);

    /**
     * 批量删除素材
     */
    Result<Void> batchDeleteAssets(List<String> assetIds);

    /**
     * 获取素材详情
     */
    Result<Map<String, Object>> getAsset(String assetId);

    /**
     * 获取剧本下的所有素材
     */
    Result<List<Map<String, Object>>> getAssetsByScript(String scriptId);

    /**
     * 获取工作空间下的素材列表
     */
    Result<List<Map<String, Object>>> listAssets(
            String workspaceId,
            String assetType);

    /**
     * 更新素材生成状态
     */
    Result<Void> updateAssetGenerationStatus(
            String assetId,
            String status,
            String errorMessage);

    /**
     * 更新素材文件信息（AI生成完成后）
     */
    Result<Void> updateAssetFileInfo(
            String assetId,
            Map<String, Object> fileInfo);

    // ==================== 实体批量查询（AI模块专用） ====================

    /**
     * 统一批量查询多类型实体
     * 用于输入解析器根据 inputSchema 批量获取角色、场景、道具、风格等实体数据
     *
     * @param request 按类型分组的实体ID列表，如 {characterIds: [...], sceneIds: [...]}
     * @return 按类型分组的实体数据
     */
    Result<Map<String, List<Map<String, Object>>>> batchQueryEntities(Map<String, List<String>> request);

    // ==================== 素材批量查询（AI模块专用） ====================

    /**
     * 批量获取素材信息
     * 用于 AI 模块解析输入中的素材ID
     *
     * @param assetIds 素材ID列表
     * @return 素材信息列表
     */
    Result<List<Map<String, Object>>> batchGetAssets(List<String> assetIds);

    /**
     * 获取素材的下载URL（带过期时间）
     *
     * @param assetId       素材ID
     * @param expireSeconds 过期时间（秒）
     * @return 预签名下载URL
     */
    Result<String> getAssetDownloadUrl(
            String assetId,
            int expireSeconds);
}
