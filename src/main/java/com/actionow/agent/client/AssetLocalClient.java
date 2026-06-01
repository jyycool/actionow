package com.actionow.agent.client;

import com.actionow.common.core.result.Result;
import com.actionow.project.dto.asset.AssetResponse;

import java.util.List;
import java.util.Map;

/**
 * 素材服务内部 本地客户端
 */
public interface AssetLocalClient {

    Result<Map<String, Object>> createAsset(String workspaceId, String userId, Map<String, Object> request);

    Result<Map<String, Object>> getAsset(String workspaceId, String assetId);

    Result<Void> deleteAsset(String workspaceId, String userId, String assetId);

    Result<List<Map<String, Object>>> batchGetAssets(List<String> assetIds);

    Result<List<AssetResponse>> batchGetAssetDetails(List<String> assetIds);

    Result<List<Map<String, Object>>> getEntityAssets(String workspaceId, String entityType, String entityId);

    Result<List<Map<String, Object>>> getEntityAssetsByType(String workspaceId, String entityType, String entityId, String relationType);

    Result<Map<String, Object>> createEntityAssetRelation(String workspaceId, String userId, Map<String, Object> request);

    Result<List<Map<String, Object>>> batchCreateAssets(String workspaceId, String userId, List<Map<String, Object>> requests);

    Result<List<Map<String, Object>>> batchCreateEntityAssetRelations(String workspaceId, String userId, List<Map<String, Object>> requests);

    Result<Void> updateAssetExtraInfo(String workspaceId, String assetId, Map<String, Object> extraInfo);

    Result<Void> updateGenerationStatus(String workspaceId, String assetId, String status);
}
