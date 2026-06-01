package com.actionow.collab.client;

import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import com.actionow.project.controller.EntityQueryInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Collab 模块访问 Project 素材能力的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class CollabAssetLocalClient implements AssetLocalClient {

    private final EntityQueryInternalController entityQueryInternalController;

    @Override
    public Result<List<Map<String, Object>>> batchGetAssets(List<String> ids) {
        Result<List<com.actionow.project.dto.EntityInfoResponse>> source =
                entityQueryInternalController.batchGetAssets(ids);
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
}
