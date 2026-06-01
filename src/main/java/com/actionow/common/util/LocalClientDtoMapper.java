package com.actionow.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilities for converting between former inter-service DTOs and local module DTOs.
 */
public final class LocalClientDtoMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    private LocalClientDtoMapper() {
    }

    public static <T> T convert(Object source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        if (targetType.isInstance(source)) {
            return targetType.cast(source);
        }
        return MAPPER.convertValue(source, targetType);
    }
}
