package com.actionow.agent.client;

import com.actionow.agent.client.dto.AssetDetailResponse;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.project.controller.EntityQueryInternalController;
import com.actionow.project.dto.asset.CreateAssetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Agent 模块访问 Project 素材能力的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class AgentAssetLocalClient implements AssetLocalClient {

    private final ObjectProvider<EntityQueryInternalController> entityQueryInternalControllerProvider;

    private EntityQueryInternalController entityQueryInternalController() {
        return entityQueryInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> createAsset(String workspaceId, String userId, Map<String, Object> request) {
        CreateAssetRequest createRequest = LocalClientDtoMapper.convert(request, CreateAssetRequest.class);
        return convert(entityQueryInternalController().createAsset(workspaceId, userId, createRequest), Map.class);
    }

    @Override
    public Result<Map<String, Object>> getAsset(String workspaceId, String assetId) {
        return convert(entityQueryInternalController().getAsset(workspaceId, assetId), Map.class);
    }

    @Override
    public Result<Void> deleteAsset(String workspaceId, String userId, String assetId) {
        return entityQueryInternalController().deleteAsset(workspaceId, userId, assetId);
    }

    @Override
    public Result<List<Map<String, Object>>> batchGetAssets(List<String> assetIds) {
        return convertMapList(entityQueryInternalController().batchGetAssets(assetIds));
    }

    @Override
    public Result<List<AssetDetailResponse>> batchGetAssetDetails(List<String> assetIds) {
        return convertList(entityQueryInternalController().batchGetAssetDetails(assetIds), AssetDetailResponse.class);
    }

    @Override
    public Result<List<Map<String, Object>>> getEntityAssets(String workspaceId, String entityType, String entityId) {
        return convertMapList(entityQueryInternalController().getEntityAssets(workspaceId, entityType, entityId));
    }

    @Override
    public Result<List<Map<String, Object>>> getEntityAssetsByType(String workspaceId, String entityType, String entityId, String relationType) {
        return convertMapList(entityQueryInternalController().getEntityAssetsByType(workspaceId, entityType, entityId, relationType));
    }

    @Override
    public Result<Map<String, Object>> createEntityAssetRelation(String workspaceId, String userId, Map<String, Object> request) {
        return convert(entityQueryInternalController().createEntityAssetRelation(workspaceId, userId, request), Map.class);
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateAssets(String workspaceId, String userId, List<Map<String, Object>> requests) {
        List<CreateAssetRequest> createRequests = requests == null
                ? List.of()
                : requests.stream().map(item -> LocalClientDtoMapper.convert(item, CreateAssetRequest.class)).toList();
        return convertMapList(entityQueryInternalController().batchCreateAssets(workspaceId, userId, createRequests));
    }

    @Override
    public Result<List<Map<String, Object>>> batchCreateEntityAssetRelations(String workspaceId, String userId, List<Map<String, Object>> requests) {
        return convertMapList(entityQueryInternalController().batchCreateEntityAssetRelations(workspaceId, userId, requests));
    }

    @Override
    public Result<Void> updateAssetExtraInfo(String workspaceId, String assetId, Map<String, Object> extraInfo) {
        return entityQueryInternalController().updateAssetExtraInfo(workspaceId, assetId, extraInfo);
    }

    @Override
    public Result<Void> updateGenerationStatus(String workspaceId, String assetId, String status) {
        return entityQueryInternalController().updateGenerationStatus(workspaceId, assetId, status);
    }

    @SuppressWarnings("unchecked")
    private Result<Map<String, Object>> convert(Result<?> source, Class<?> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        return Result.success((Map<String, Object>) LocalClientDtoMapper.convert(source.getData(), targetType), source.getMessage());
    }

    private Result<List<Map<String, Object>>> convertMapList(Result<? extends List<?>> source) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        List<Map<String, Object>> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, Map.class))
                .map(item -> (Map<String, Object>) item)
                .toList();
        return Result.success(converted, source.getMessage());
    }

    private <T> Result<List<T>> convertList(Result<? extends List<?>> source, Class<T> targetType) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Project 本地调用失败");
        }
        List<T> converted = source.getData() == null
                ? List.of()
                : source.getData().stream()
                .map(item -> LocalClientDtoMapper.convert(item, targetType))
                .toList();
        return Result.success(converted, source.getMessage());
    }
}
