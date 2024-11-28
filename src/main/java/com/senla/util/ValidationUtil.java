package com.senla.util;

import java.time.LocalDateTime;

public final class ValidationUtil {
    public static String validateNotNullOrEmpty(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Field must not be null or empty");
        }
        return value;
    }

    public static <T> T validateNotNull(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Field must not be null");
        }
        return value;
    }

    private ValidationUtil() {}
}
