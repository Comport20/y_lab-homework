package com.coworkingservice.memorydb.credential;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Credential;
import com.coworkingservice.memorydb.ReadWhereEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRead implements ReadWhereEntity<Credential> {
    @Override
    public int readWhereEntity(Credential credential) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.credential where username=? and password=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setString(1, credential.getUsername());
                preparedStatement.setString(2, credential.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("person_id");
                    con.commit();
                    return id;
                }
                throw new SQLException();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
