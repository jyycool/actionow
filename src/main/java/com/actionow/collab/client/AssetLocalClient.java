package com.actionow.collab.client;

import com.actionow.common.core.result.Result;

import java.util.List;
import java.util.Map;

/**
 * 资产服务 本地客户端
 * 备用接口 — 主路径通过前端传入冗余元数据，不依赖运行时 Local 调用
 *
 * @author Actionow
 */
public interface AssetLocalClient {

    /**
     * 批量查询资产元数据
     *
     * @param ids 资产ID列表
     * @return 资产元数据列表
     */
    Result<List<Map<String, Object>>> batchGetAssets(List<String> ids);
}
