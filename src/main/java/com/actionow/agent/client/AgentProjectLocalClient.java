package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.project.controller.EntityQueryInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Agent 模块访问 Project 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class AgentProjectLocalClient implements ProjectLocalClient {

    private final ObjectProvider<EntityQueryInternalController> entityQueryInternalControllerProvider;

    private EntityQueryInternalController entityQueryInternalController() {
        return entityQueryInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> getScript(String scriptId) {
        return convertMapListFirst(entityQueryInternalController().batchGetScripts(List.of(scriptId)));
    }

    @Override
    public Result<Map<String, Object>> getEpisode(String episodeId) {
        return convertMapListFirst(entityQueryInternalController().batchGetEpisodes(List.of(episodeId)));
    }

    @Override
    public Result<Map<String, Object>> getStoryboard(String storyboardId) {
        return entityQueryInternalController().getStoryboardDetail(storyboardId);
    }

    @Override
    public Result<Map<String, Object>> getCharacter(String characterId) {
        return entityQueryInternalController().getCharacter(characterId);
    }

    @Override
    public Result<Map<String, Object>> getScene(String sceneId) {
        return entityQueryInternalController().getScene(sceneId);
    }

    @Override
    public Result<Map<String, Object>> getProp(String propId) {
        return entityQueryInternalController().getProp(propId);
    }

    @Override
    public Result<Map<String, Object>> getStyle(String styleId) {
        return entityQueryInternalController().getStyle(styleId);
    }

    @Override
    public Result<Map<String, List<Map<String, Object>>>> batchQueryEntities(Map<String, List<String>> request) {
        Result<Map<String, List<com.actionow.project.dto.EntityInfoResponse>>> source = entityQueryInternalController().batchQueryEntities(request);
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        Map<String, List<Map<String, Object>>> data = source.getData() == null
                ? Map.of()
                : source.getData().entrySet().stream().collect(java.util.stream.Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue() == null ? List.of() : entry.getValue().stream()
                        .map(item -> LocalClientDtoMapper.convert(item, Map.class))
                        .map(item -> (Map<String, Object>) item)
                        .toList()
        ));
        return Result.success(data, source.getMessage());
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateEpisodes(String workspaceId, String userId, String scriptId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreateEpisodeRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreateEpisodeRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateEpisodes(workspaceId, userId, scriptId, typed));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateStoryboards(String workspaceId, String userId, String episodeId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreateStoryboardRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreateStoryboardRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateStoryboards(workspaceId, userId, episodeId, typed));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateCharacters(String workspaceId, String userId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreateCharacterRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreateCharacterRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateCharacters(workspaceId, userId, typed));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateScenes(String workspaceId, String userId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreateSceneRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreateSceneRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateScenes(workspaceId, userId, typed));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateProps(String workspaceId, String userId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreatePropRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreatePropRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateProps(workspaceId, userId, typed));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateStyles(String workspaceId, String userId, List<Map<String, Object>> requests) {
        List<com.actionow.project.dto.CreateStyleRequest> typed = convertRequestList(requests, com.actionow.project.dto.CreateStyleRequest.class);
        return convertMapList(entityQueryInternalController().batchCreateStyles(workspaceId, userId, typed));
    }

    @Override public Result<Map<String, Object>> createScript(Map<String, Object> request) { return unsupported("createScript"); }
    @Override public Result<Map<String, Object>> updateScript(String scriptId, Map<String, Object> request) { return unsupported("updateScript"); }
    @Override public Result<List<Map<String, Object>>> listScripts() { return unsupported("listScripts"); }
    @Override public Result<Map<String, Object>> queryScripts(String keyword, String status, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryScripts"); }
    @Override public Result<Map<String, Object>> createEpisode(Map<String, Object> request) { return unsupported("createEpisode"); }
    @Override public Result<Map<String, Object>> updateEpisode(String episodeId, Map<String, Object> request) { return unsupported("updateEpisode"); }
    @Override public Result<List<Map<String, Object>>> listEpisodesByScript(String scriptId, String keyword, Integer limit) { return unsupported("listEpisodesByScript"); }
    @Override public Result<Void> deleteEpisode(String episodeId) { return unsupported("deleteEpisode"); }
    @Override public Result<Map<String, Object>> queryEpisodes(String scriptId, String status, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryEpisodes"); }
    @Override public Result<Map<String, Object>> createStoryboard(Map<String, Object> request) { return unsupported("createStoryboard"); }
    @Override public Result<Map<String, Object>> updateStoryboard(String storyboardId, Map<String, Object> request) { return unsupported("updateStoryboard"); }
    @Override public Result<List<Map<String, Object>>> listStoryboardsByEpisode(String episodeId, String keyword, Integer limit) { return unsupported("listStoryboardsByEpisode"); }
    @Override public Result<Void> deleteStoryboard(String storyboardId) { return unsupported("deleteStoryboard"); }
    @Override public Result<Map<String, Object>> queryStoryboards(String scriptId, String episodeId, String status, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryStoryboards"); }
    @Override public Result<Map<String, Object>> getStoryboardRelations(String storyboardId) { return unsupported("getStoryboardRelations"); }
    @Override public Result<Map<String, Object>> createCharacter(Map<String, Object> request) { return unsupported("createCharacter"); }
    @Override public Result<Map<String, Object>> updateCharacter(String characterId, Map<String, Object> request) { return unsupported("updateCharacter"); }
    @Override public Result<List<Map<String, Object>>> listAvailableCharacters(String scriptId, String keyword, Integer limit) { return unsupported("listAvailableCharacters"); }
    @Override public Result<Void> deleteCharacter(String characterId) { return unsupported("deleteCharacter"); }
    @Override public Result<Map<String, Object>> queryCharacters(String scope, String scriptId, String characterType, String gender, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryCharacters"); }
    @Override public Result<Map<String, Object>> createScene(Map<String, Object> request) { return unsupported("createScene"); }
    @Override public Result<List<Map<String, Object>>> listAvailableScenes(String scriptId, String keyword, Integer limit) { return unsupported("listAvailableScenes"); }
    @Override public Result<Map<String, Object>> queryScenes(String scope, String scriptId, String sceneType, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryScenes"); }
    @Override public Result<Map<String, Object>> updateScene(String sceneId, Map<String, Object> request) { return unsupported("updateScene"); }
    @Override public Result<Void> deleteScene(String sceneId) { return unsupported("deleteScene"); }
    @Override public Result<Map<String, Object>> createProp(Map<String, Object> request) { return unsupported("createProp"); }
    @Override public Result<List<Map<String, Object>>> listAvailableProps(String scriptId, String keyword, Integer limit) { return unsupported("listAvailableProps"); }
    @Override public Result<Map<String, Object>> queryProps(String scope, String scriptId, String propType, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryProps"); }
    @Override public Result<Map<String, Object>> updateProp(String propId, Map<String, Object> request) { return unsupported("updateProp"); }
    @Override public Result<Void> deleteProp(String propId) { return unsupported("deleteProp"); }
    @Override public Result<Map<String, Object>> createStyle(Map<String, Object> request) { return unsupported("createStyle"); }
    @Override public Result<List<Map<String, Object>>> listAvailableStyles(String scriptId, String keyword, Integer limit) { return unsupported("listAvailableStyles"); }
    @Override public Result<Map<String, Object>> queryStyles(String scope, String scriptId, String keyword, Integer pageNum, Integer pageSize, String orderBy, String orderDir) { return unsupported("queryStyles"); }
    @Override public Result<Map<String, Object>> updateStyle(String styleId, Map<String, Object> request) { return unsupported("updateStyle"); }
    @Override public Result<Void> deleteStyle(String styleId) { return unsupported("deleteStyle"); }
    @Override public Result<Map<String, Object>> queryAssets(String keyword, String scriptId, String assetType, String source, String generationStatus, String scope, Integer page, Integer size) { return unsupported("queryAssets"); }
    @Override public Result<Map<String, Object>> updateAsset(String assetId, Map<String, Object> request) { return unsupported("updateAsset"); }
    @Override public Result<Map<String, Object>> createEntityRelation(Map<String, Object> request) { return unsupported("createEntityRelation"); }
    @Override public Result<List<Map<String, Object>>> batchCreateEntityRelations(List<Map<String, Object>> requests) { return unsupported("batchCreateEntityRelations"); }
    @Override public Result<Map<String, Object>> updateEntityRelation(String relationId, Map<String, Object> request) { return unsupported("updateEntityRelation"); }
    @Override public Result<Void> deleteEntityRelation(String relationId) { return unsupported("deleteEntityRelation"); }
    @Override public Result<List<Map<String, Object>>> listRelationsBySource(String sourceType, String sourceId) { return unsupported("listRelationsBySource"); }
    @Override public Result<List<Map<String, Object>>> listRelationsBySourceAndType(String sourceType, String sourceId, String relationType) { return unsupported("listRelationsBySourceAndType"); }
    @Override public Result<List<Map<String, Object>>> listRelationsByTarget(String targetType, String targetId) { return unsupported("listRelationsByTarget"); }
    @Override public Result<Map<String, Object>> getOrCreateEntityRelation(Map<String, Object> request) { return unsupported("getOrCreateEntityRelation"); }

    private Result<Map<String, Object>> convertMapListFirst(Result<? extends List<?>> source) {
        Result<List<Map<String, Object>>> listResult = convertMapList(source);
        if (listResult == null || !listResult.isSuccess()) {
            return Result.fail(listResult != null ? listResult.getCode() : "500", listResult != null ? listResult.getMessage() : "Project 本地调用失败");
        }
        return Result.success(listResult.getData().isEmpty() ? Map.of() : listResult.getData().get(0), listResult.getMessage());
    }

    private Result<List<Map<String, Object>>> convertMapList(Result<? extends List<?>> source) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500", source != null ? source.getMessage() : "Project 本地调用失败");
        }
        List<Map<String, Object>> converted = source.getData() == null ? List.of() : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, Map.class))
                .map(item -> (Map<String, Object>) item)
                .toList();
        return Result.success(converted, source.getMessage());
    }

    private <T> List<T> convertRequestList(List<Map<String, Object>> requests, Class<T> targetType) {
        return requests == null ? List.of() : requests.stream()
                .map(item -> LocalClientDtoMapper.convert(item, targetType))
                .toList();
    }

    private <T> Result<T> unsupported(String methodName) {
        return Result.fail("501", "AgentProjectLocalClient does not support " + methodName);
    }
}
