package com.actionow.ai.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.project.controller.EntityQueryInternalController;
import com.actionow.project.dto.EntityInfoResponse;
import com.actionow.project.dto.asset.AssetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * AI 模块访问 Project 域的本地适配器。
 *
 * <p>单体应用内显式调用 Project 内部控制器，避免依赖 FormerLocalClientConfiguration
 * 的反射式兜底适配。</p>
 */
@Component
@RequiredArgsConstructor
public class AiProjectLocalClient implements ProjectLocalClient {

    private final EntityQueryInternalController entityQueryInternalController;

    @Override
    public Result<Map<String, Object>> createScript(Map<String, Object> request) {
        return unsupported("createScript");
    }

    @Override
    public Result<List<String>> batchCreateScripts(List<Map<String, Object>> scripts) {
        return unsupported("batchCreateScripts");
    }

    @Override
    public Result<Void> updateScript(String scriptId, Map<String, Object> request) {
        return unsupported("updateScript");
    }

    @Override
    public Result<Void> deleteScript(String scriptId) {
        return unsupported("deleteScript");
    }

    @Override
    public Result<Void> batchDeleteScripts(List<String> scriptIds) {
        return unsupported("batchDeleteScripts");
    }

    @Override
    public Result<Map<String, Object>> getScript(String scriptId) {
        return unsupported("getScript");
    }

    @Override
    public Result<List<Map<String, Object>>> listScripts(String workspaceId) {
        return unsupported("listScripts");
    }

    @Override
    public Result<Map<String, Object>> createEpisode(Map<String, Object> request) {
        return unsupported("createEpisode");
    }

    @Override
    public Result<List<String>> batchCreateEpisodes(List<Map<String, Object>> episodes) {
        return unsupported("batchCreateEpisodes");
    }

    @Override
    public Result<Void> updateEpisode(String episodeId, Map<String, Object> request) {
        return unsupported("updateEpisode");
    }

    @Override
    public Result<Void> deleteEpisode(String episodeId) {
        return unsupported("deleteEpisode");
    }

    @Override
    public Result<Void> batchDeleteEpisodes(List<String> episodeIds) {
        return unsupported("batchDeleteEpisodes");
    }

    @Override
    public Result<Map<String, Object>> getEpisode(String episodeId) {
        return unsupported("getEpisode");
    }

    @Override
    public Result<List<Map<String, Object>>> getEpisodesByScript(String scriptId) {
        return unsupported("getEpisodesByScript");
    }

    @Override
    public Result<Map<String, Object>> createStoryboard(Map<String, Object> request) {
        return unsupported("createStoryboard");
    }

    @Override
    public Result<List<String>> batchCreateStoryboards(List<Map<String, Object>> storyboards) {
        return unsupported("batchCreateStoryboards");
    }

    @Override
    public Result<Void> updateStoryboard(String storyboardId, Map<String, Object> request) {
        return unsupported("updateStoryboard");
    }

    @Override
    public Result<Void> deleteStoryboard(String storyboardId) {
        return unsupported("deleteStoryboard");
    }

    @Override
    public Result<Void> batchDeleteStoryboards(List<String> storyboardIds) {
        return unsupported("batchDeleteStoryboards");
    }

    @Override
    public Result<Map<String, Object>> getStoryboard(String storyboardId) {
        return entityQueryInternalController.getStoryboardDetail(storyboardId);
    }

    @Override
    public Result<List<Map<String, Object>>> getStoryboardsByEpisode(String episodeId) {
        return entityQueryInternalController.getStoryboardsForEpisode(episodeId);
    }

    @Override
    public Result<Map<String, Object>> createCharacter(Map<String, Object> request) {
        return unsupported("createCharacter");
    }

    @Override
    public Result<List<String>> batchCreateCharacters(List<Map<String, Object>> characters) {
        return unsupported("batchCreateCharacters");
    }

    @Override
    public Result<Void> updateCharacter(String characterId, Map<String, Object> request) {
        return unsupported("updateCharacter");
    }

    @Override
    public Result<Void> deleteCharacter(String characterId) {
        return unsupported("deleteCharacter");
    }

    @Override
    public Result<Void> batchDeleteCharacters(List<String> characterIds) {
        return unsupported("batchDeleteCharacters");
    }

    @Override
    public Result<Map<String, Object>> getCharacter(String characterId) {
        return entityQueryInternalController.getCharacter(characterId);
    }

    @Override
    public Result<List<Map<String, Object>>> getCharactersByScript(String scriptId) {
        return entityQueryInternalController.getCharactersForScript(scriptId);
    }

    @Override
    public Result<Map<String, Object>> createScene(Map<String, Object> request) {
        return unsupported("createScene");
    }

    @Override
    public Result<List<String>> batchCreateScenes(List<Map<String, Object>> scenes) {
        return unsupported("batchCreateScenes");
    }

    @Override
    public Result<Void> updateScene(String sceneId, Map<String, Object> request) {
        return unsupported("updateScene");
    }

    @Override
    public Result<Void> deleteScene(String sceneId) {
        return unsupported("deleteScene");
    }

    @Override
    public Result<Void> batchDeleteScenes(List<String> sceneIds) {
        return unsupported("batchDeleteScenes");
    }

    @Override
    public Result<Map<String, Object>> getScene(String sceneId) {
        return entityQueryInternalController.getScene(sceneId);
    }

    @Override
    public Result<List<Map<String, Object>>> getScenesByScript(String scriptId) {
        return entityQueryInternalController.getScenesForScript(scriptId);
    }

    @Override
    public Result<Map<String, Object>> createProp(Map<String, Object> request) {
        return unsupported("createProp");
    }

    @Override
    public Result<List<String>> batchCreateProps(List<Map<String, Object>> props) {
        return unsupported("batchCreateProps");
    }

    @Override
    public Result<Void> updateProp(String propId, Map<String, Object> request) {
        return unsupported("updateProp");
    }

    @Override
    public Result<Void> deleteProp(String propId) {
        return unsupported("deleteProp");
    }

    @Override
    public Result<Void> batchDeleteProps(List<String> propIds) {
        return unsupported("batchDeleteProps");
    }

    @Override
    public Result<Map<String, Object>> getProp(String propId) {
        return entityQueryInternalController.getProp(propId);
    }

    @Override
    public Result<List<Map<String, Object>>> getPropsByScript(String scriptId) {
        return entityQueryInternalController.getPropsForScript(scriptId);
    }

    @Override
    public Result<Map<String, Object>> createStyle(Map<String, Object> request) {
        return unsupported("createStyle");
    }

    @Override
    public Result<List<String>> batchCreateStyles(List<Map<String, Object>> styles) {
        return unsupported("batchCreateStyles");
    }

    @Override
    public Result<Void> updateStyle(String styleId, Map<String, Object> request) {
        return unsupported("updateStyle");
    }

    @Override
    public Result<Void> deleteStyle(String styleId) {
        return unsupported("deleteStyle");
    }

    @Override
    public Result<Void> batchDeleteStyles(List<String> styleIds) {
        return unsupported("batchDeleteStyles");
    }

    @Override
    public Result<Map<String, Object>> getStyle(String styleId) {
        return entityQueryInternalController.getStyle(styleId);
    }

    @Override
    public Result<List<Map<String, Object>>> getStylesByScript(String scriptId) {
        return entityQueryInternalController.getStylesForScript(scriptId);
    }

    @Override
    public Result<Map<String, Object>> createAsset(Map<String, Object> request) {
        return unsupported("createAsset");
    }

    @Override
    public Result<List<String>> batchCreateAssets(List<Map<String, Object>> assets) {
        return unsupported("batchCreateAssets");
    }

    @Override
    public Result<Void> updateAsset(String assetId, Map<String, Object> request) {
        return unsupported("updateAsset");
    }

    @Override
    public Result<Void> deleteAsset(String assetId) {
        return unsupported("deleteAsset");
    }

    @Override
    public Result<Void> batchDeleteAssets(List<String> assetIds) {
        return unsupported("batchDeleteAssets");
    }

    @Override
    public Result<Map<String, Object>> getAsset(String assetId) {
        return unsupported("getAsset");
    }

    @Override
    public Result<List<Map<String, Object>>> getAssetsByScript(String scriptId) {
        return unsupported("getAssetsByScript");
    }

    @Override
    public Result<List<Map<String, Object>>> listAssets(String workspaceId, String assetType) {
        return unsupported("listAssets");
    }

    @Override
    public Result<Void> updateAssetGenerationStatus(String assetId, String status, String errorMessage) {
        return unsupported("updateAssetGenerationStatus");
    }

    @Override
    public Result<Void> updateAssetFileInfo(String assetId, Map<String, Object> fileInfo) {
        return unsupported("updateAssetFileInfo");
    }

    @Override
    public Result<Map<String, List<Map<String, Object>>>> batchQueryEntities(Map<String, List<String>> request) {
        Result<Map<String, List<EntityInfoResponse>>> source = entityQueryInternalController.batchQueryEntities(request);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "Project 本地调用失败");
        }
        return Result.success(convertEntityMap(source.getData()), source.getMessage());
    }

    @Override
    public Result<List<Map<String, Object>>> batchGetAssets(List<String> assetIds) {
        Result<List<AssetResponse>> source = entityQueryInternalController.batchGetAssetDetails(assetIds);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "Project 本地调用失败");
        }
        List<Map<String, Object>> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, Map.class))
                .map(item -> (Map<String, Object>) item)
                .toList();
        return Result.success(converted, source.getMessage());
    }

    @Override
    public Result<String> getAssetDownloadUrl(String assetId, int expireSeconds) {
        Result<List<AssetResponse>> source = entityQueryInternalController.batchGetAssetDetails(List.of(assetId));
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "Project 本地调用失败");
        }
        if (source.getData() == null || source.getData().isEmpty() || source.getData().get(0).getFileUrl() == null) {
            return Result.fail("404", "素材不存在或缺少下载 URL");
        }
        return Result.success(source.getData().get(0).getFileUrl());
    }

    private Map<String, List<Map<String, Object>>> convertEntityMap(Map<String, List<EntityInfoResponse>> data) {
        if (data == null || data.isEmpty()) {
            return Map.of();
        }
        return data.entrySet().stream().collect(java.util.stream.Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue() == null ? List.of() : entry.getValue().stream()
                        .map(item -> LocalClientDtoMapper.convert(item, Map.class))
                        .map(item -> (Map<String, Object>) item)
                        .toList()
        ));
    }

    private <T> Result<T> unsupported(String methodName) {
        return Result.fail("501", "AiProjectLocalClient does not support " + methodName);
    }
}
