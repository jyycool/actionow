package com.actionow.task.client;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 项目服务 本地客户端
 * 用于批量查询剧本信息、Scope 展开（集/分镜/角色/场景/道具列表）
 *
 * @author Actionow
 */
public interface ProjectLocalClient {

    /**
     * 批量获取剧本信息
     */
    Result<List<Map<String, Object>>> batchGetScripts(List<String> ids);

    // ==================== Scope 展开: Episode ====================

    /**
     * 列出剧本下的所有集
     */
    Result<List<Map<String, Object>>> listEpisodesByScript(
            String scriptId,
            Integer limit);

    // ==================== Scope 展开: Storyboard ====================

    /**
     * 列出某集下的所有分镜
     */
    Result<List<Map<String, Object>>> listStoryboardsByEpisode(
            String episodeId,
            Integer limit);

    // ==================== Scope 展开: Character / Scene / Prop ====================

    /**
     * 列出剧本下可用的角色
     */
    Result<List<Map<String, Object>>> listAvailableCharacters(
            String scriptId,
            Integer limit);

    /**
     * 列出剧本下可用的场景
     */
    Result<List<Map<String, Object>>> listAvailableScenes(
            String scriptId,
            Integer limit);

    /**
     * 列出剧本下可用的道具
     */
    Result<List<Map<String, Object>>> listAvailableProps(
            String scriptId,
            Integer limit);
}
