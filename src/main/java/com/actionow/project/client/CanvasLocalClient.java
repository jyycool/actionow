package com.actionow.project.client;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Canvas 服务 本地客户端
 * 调用 actionow-canvas 的内部接口
 *
 * 统一主画布模型：1 Script = 1 Canvas
 * 所有实体都归属于 Script 的唯一 Canvas
 *
 * @author Actionow
 */
public interface CanvasLocalClient {

    /**
     * 初始化剧本画布
     * 创建画布并添加默认剧本节点
     *
     * @param scriptId    剧本ID
     * @param workspaceId 工作空间ID
     * @param scriptName  剧本名称（可选）
     * @return 画布信息
     */
    Result<Map<String, Object>> initScriptCanvas(
            String scriptId,
            String workspaceId,
            String scriptName);

    /**
     * 获取或创建剧本画布
     *
     * @param scriptId    剧本ID
     * @param workspaceId 工作空间ID
     * @return 画布信息
     */
    Result<Map<String, Object>> getOrCreateByScriptId(
            String scriptId,
            String workspaceId);

    /**
     * 检查剧本画布是否存在
     *
     * @param scriptId 剧本ID
     * @return 是否存在
     */
    Result<Boolean> existsByScriptId(String scriptId);

    /**
     * 删除剧本画布
     *
     * @param scriptId 剧本ID
     * @param userId   操作用户ID（可选）
     * @return 操作结果
     */
    Result<Void> deleteByScriptId(
            String scriptId,
            String userId);

    /**
     * 创建实体节点
     * 用于 MQ 同步时创建节点
     *
     * @param request     节点创建请求
     * @param workspaceId 工作空间ID
     * @return 创建的节点信息
     */
    Result<Map<String, Object>> createNode(
            Map<String, Object> request,
            String workspaceId);
}
