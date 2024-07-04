package com.coworkingservice;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionDB {
    private static String urlFile = "src/main/resources/db/changelog/liquibase.properties";
    private static String jdbcURL;
    public ConnectionDB(String urlFile, String jdbcURL) {
        ConnectionDB.urlFile = urlFile;
        ConnectionDB.jdbcURL = jdbcURL;
    }
    public static Connection getConnection() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(urlFile)) {
            prop.load(input);
            String url = Optional.ofNullable(jdbcURL).orElse(prop.getProperty("url"));
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
