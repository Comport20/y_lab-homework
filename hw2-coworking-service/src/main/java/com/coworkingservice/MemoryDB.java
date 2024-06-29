package com.coworkingservice;

import com.coworkingservice.entity.*;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

@Getter
public class MemoryDB {
    private static MemoryDB instance;
    private final Map<Long, Room> roomMapTable;
    private final Map<Credential, Person> personMapTable;
    private final List<Slot> reservedSlotListTable;

    private MemoryDB() {
        this.roomMapTable = new TreeMap<>();
        this.personMapTable = new HashMap<>();
        this.reservedSlotListTable = new LinkedList<>();
    }

    public static MemoryDB getInstance() {
        if (instance == null) {
            synchronized (MemoryDB.class) {
                if (instance == null) {
                    instance = new MemoryDB();
                }
            }
        }
        return instance;
    }

    public static Connection getConnection() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/db/changelog/liquibase.properties")) {
            prop.load(input);
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
