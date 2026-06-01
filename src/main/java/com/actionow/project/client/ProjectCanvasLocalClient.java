package com.actionow.project.client;

import com.actionow.canvas.controller.CanvasInternalController;
import com.actionow.canvas.dto.canvas.CanvasResponse;
import com.actionow.canvas.dto.node.CanvasNodeResponse;
import com.actionow.canvas.dto.node.CreateNodeRequest;
import com.actionow.common.core.result.Result;
import com.actionow.common.util.LocalClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Project 模块访问 Canvas 域的本地适配器。
 */
@Component
@RequiredArgsConstructor
public class ProjectCanvasLocalClient implements CanvasLocalClient {

    private final ObjectProvider<CanvasInternalController> canvasInternalControllerProvider;

    private CanvasInternalController canvasInternalController() {
        return canvasInternalControllerProvider.getObject();
    }

    @Override
    public Result<Map<String, Object>> initScriptCanvas(String scriptId, String workspaceId, String scriptName) {
        return convert(canvasInternalController().initScriptCanvas(scriptId, workspaceId, scriptName));
    }

    @Override
    public Result<Map<String, Object>> getOrCreateByScriptId(String scriptId, String workspaceId) {
        return convert(canvasInternalController().getOrCreateByScriptId(scriptId, workspaceId));
    }

    @Override
    public Result<Boolean> existsByScriptId(String scriptId) {
        return canvasInternalController().existsByScriptId(scriptId);
    }

    @Override
    public Result<Void> deleteByScriptId(String scriptId, String userId) {
        return canvasInternalController().deleteByScriptId(scriptId, userId);
    }

    @Override
    public Result<Map<String, Object>> createNode(Map<String, Object> request, String workspaceId) {
        CreateNodeRequest createNodeRequest = LocalClientDtoMapper.convert(request, CreateNodeRequest.class);
        return convert(canvasInternalController().createNode(createNodeRequest, workspaceId));
    }

    private Result<Map<String, Object>> convert(Result<?> source) {
        if (source == null || !source.isSuccess()) {
            return Result.fail(source != null ? source.getCode() : "500",
                    source != null ? source.getMessage() : "Canvas 本地调用失败");
        }
        Map<String, Object> data = source.getData() == null
                ? null
                : LocalClientDtoMapper.convert(source.getData(), Map.class);
        return Result.success(data, source.getMessage());
    }
}
