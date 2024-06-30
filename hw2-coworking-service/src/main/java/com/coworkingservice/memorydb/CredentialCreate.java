package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Credential;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CredentialCreate implements Create<Credential>{
    @Override
    public void create(Credential credential) {
        try (Connection con = MemoryDB.getConnection()) {
            con.setAutoCommit(false);
            String insertQuery = "INSERT INTO entity.credential (username, password, person_id) VALUES(?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, credential.getUsername());
                preparedStatement.setString(2, credential.getPassword());
                preparedStatement.setInt(3, credential.getPersonId());
                preparedStatement.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }
}
