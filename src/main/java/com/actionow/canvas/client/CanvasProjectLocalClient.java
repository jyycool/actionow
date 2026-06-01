package com.actionow.canvas.client;

import com.actionow.canvas.dto.*;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.project.controller.CanvasInternalController;
import com.actionow.project.controller.EntityQueryInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Canvas 模块访问 Project 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class CanvasProjectLocalClient implements ProjectLocalClient {

    private final ObjectProvider<CanvasInternalController> canvasInternalControllerProvider;
    private final ObjectProvider<EntityQueryInternalController> entityQueryInternalControllerProvider;

    private CanvasInternalController canvasInternalController() {
        return canvasInternalControllerProvider.getObject();
    }

    private EntityQueryInternalController entityQueryInternalController() {
        return entityQueryInternalControllerProvider.getObject();
    }

    @Override
    public Result<CanvasEntityCreateResponse> createEntity(CanvasEntityCreateRequest request) {
        com.actionow.project.dto.CanvasEntityCreateRequest projectRequest =
                LocalClientDtoMapper.convert(request, com.actionow.project.dto.CanvasEntityCreateRequest.class);
        return convert(canvasInternalController().createEntityFromCanvas(projectRequest), CanvasEntityCreateResponse.class);
    }

    @Override
    public Result<BatchEntityCreateResponse> batchCreateEntities(BatchEntityCreateRequest request) {
        com.actionow.project.dto.BatchEntityCreateRequest projectRequest =
                LocalClientDtoMapper.convert(request, com.actionow.project.dto.BatchEntityCreateRequest.class);
        return convert(canvasInternalController().batchCreateEntities(projectRequest), BatchEntityCreateResponse.class);
    }

    @Override
    public Result<CanvasEntityUpdateResponse> updateEntity(CanvasEntityUpdateRequest request) {
        com.actionow.project.dto.CanvasEntityUpdateRequest projectRequest =
                LocalClientDtoMapper.convert(request, com.actionow.project.dto.CanvasEntityUpdateRequest.class);
        return convert(canvasInternalController().updateEntityFromCanvas(projectRequest), CanvasEntityUpdateResponse.class);
    }

    @Override
    public Result<BatchEntityUpdateResponse> batchUpdateEntities(BatchEntityUpdateRequest request) {
        com.actionow.project.dto.BatchEntityUpdateRequest projectRequest =
                LocalClientDtoMapper.convert(request, com.actionow.project.dto.BatchEntityUpdateRequest.class);
        return convert(canvasInternalController().batchUpdateEntities(projectRequest), BatchEntityUpdateResponse.class);
    }

    @Override
    public Result<Void> deleteEntity(String entityType, String entityId) {
        return canvasInternalController().deleteEntityFromCanvas(entityType, entityId);
    }

    @Override
    public Result<List<EntityInfo>> batchGetScripts(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetScripts(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetEpisodes(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetEpisodes(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetStoryboards(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetStoryboards(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetCharacters(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetCharacters(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetScenes(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetScenes(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetProps(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetProps(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetAssets(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetAssets(ids));
    }

    @Override
    public Result<List<EntityInfo>> batchGetStyles(List<String> ids) {
        return convertList(entityQueryInternalController().batchGetStyles(ids));
    }

    @Override
    public Result<List<EntityInfo>> getEntitiesByScript(String scriptId, List<String> entityTypes) {
        return convertList(entityQueryInternalController().getEntitiesByScript(scriptId, entityTypes));
    }

    @Override
    public Result<List<EntityInfo>> getEntitiesByEpisode(String episodeId, List<String> entityTypes) {
        return convertList(entityQueryInternalController().getEntitiesByEpisode(episodeId, entityTypes));
    }

    @Override
    public Result<List<EntityInfo>> getAssetsByCharacter(String characterId) {
        return getRelatedAssets("CHARACTER", characterId);
    }

    @Override
    public Result<List<EntityInfo>> getAssetsByScene(String sceneId) {
        return getRelatedAssets("SCENE", sceneId);
    }

    @Override
    public Result<List<EntityInfo>> getAssetsByProp(String propId) {
        return getRelatedAssets("PROP", propId);
    }

    @Override
    public Result<List<EntityInfo>> getAssetsByStoryboard(String storyboardId) {
        return getRelatedAssets("STORYBOARD", storyboardId);
    }

    @Override
    public Result<List<EntityInfo>> getRelatedAssets(String entityType, String entityId) {
        return convertList(entityQueryInternalController().getEntityAssets(null, entityType, entityId));
    }

    @Override
    public Result<EntityAssetRelationResponse> createEntityAssetRelation(CreateEntityAssetRelationRequest request) {
        com.actionow.project.dto.relation.CreateEntityAssetRelationRequest projectRequest =
                LocalClientDtoMapper.convert(request, com.actionow.project.dto.relation.CreateEntityAssetRelationRequest.class);
        return convert(canvasInternalController().createEntityAssetRelation(projectRequest), EntityAssetRelationResponse.class);
    }

    private <T> Result<T> convert(Result<?> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        return Result.success(LocalClientDtoMapper.convert(source.getData(), targetType), source.getMessage());
    }

    private Result<List<EntityInfo>> convertList(Result<? extends List<?>> source) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        List<EntityInfo> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, EntityInfo.class))
                .toList();
        return Result.success(converted, source.getMessage());
    }
}
