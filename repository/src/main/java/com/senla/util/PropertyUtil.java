package com.senla.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertyUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadFile();
    }

    private PropertyUtil() {
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadFile() {
        try (var stream = PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
