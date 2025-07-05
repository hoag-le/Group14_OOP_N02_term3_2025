package com.example.servingwebcontent.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AivenDatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(AivenDatabaseConfig.class);
    private static final String URL = System.getenv("AIVEN_JDBC_URL");
    private static final String USER = System.getenv("AIVEN_DB_USER");
    private static final String PASSWORD = System.getenv("AIVEN_DB_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}