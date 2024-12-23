package com.senla.api.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private ConnectionManager() {
    }

    public static Connection open() {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    PropertyUtil.getProperty(URL_KEY),
                    PropertyUtil.getProperty(USERNAME_KEY),
                    PropertyUtil.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
