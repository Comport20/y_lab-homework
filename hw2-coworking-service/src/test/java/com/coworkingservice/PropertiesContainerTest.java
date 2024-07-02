package com.coworkingservice;


import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Testcontainers
public class PropertiesContainerTest {
    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("coworkingdb")
                    .withUsername("coworkinguser")
                    .withPassword("coworkingpassword");
    @BeforeAll
    static void beforeAll(){
        ConnectionDB connectionDB = new ConnectionDB("src/test/resources/changelogs/liquibase.properties",postgreSQLContainer.getJdbcUrl());
        try (Connection conn = connectionDB.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new Liquibase("changelogs/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void containerIsRunning() {
        assertTrue(postgreSQLContainer.isRunning());
    }
}
