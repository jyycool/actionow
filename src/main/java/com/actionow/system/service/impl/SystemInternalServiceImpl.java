package com.actionow.system.service.impl;

import com.actionow.system.mapper.SystemConfigMapper;
import com.actionow.system.service.PlatformStatsService;
import com.actionow.system.service.SystemConfigService;
import com.actionow.system.service.SystemInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统内部服务实现
 *
 * @author Actionow
 */
@Service
@RequiredArgsConstructor
public class SystemInternalServiceImpl implements SystemInternalService {

    private final PlatformStatsService statsService;
    private final SystemConfigService configService;
    private final SystemConfigMapper configMapper;

    @Override
    public void recordStats(String metricType, Long value, String workspaceId) {
        statsService.recordStats(metricType, value, workspaceId);
    }

    @Override
    public String getConfigValue(String configKey) {
        return configService.getConfigValue(configKey, "GLOBAL", null);
    }

    @Override
    public Map<String, String> getConfigBatch(String prefix) {
        Map<String, String> result = new LinkedHashMap<>();
        configMapper.selectByKeyPrefix(prefix)
                .forEach(c -> result.put(c.getConfigKey(), c.getConfigValue()));
        return result;
    }
}