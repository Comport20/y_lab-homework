package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CredentialRead implements ReadWhereEntity<Credential>{
    @Override
    public int readWhereEntity(Credential credential) {
        try (Connection con = MemoryDB.getConnection()) {
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
