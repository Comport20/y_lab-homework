package com.coworkingservice.memorydb.person;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonRead implements ReadWhereString<Person>, Read<Person> {
    private final EntityFamilyReadingFabric entityFamilyReadingFabric;
    public PersonRead(EntityFamilyReadingFabric entityFamilyReadingFabric) {
        this.entityFamilyReadingFabric = entityFamilyReadingFabric;
    }
    @Override
    public Person readWhereString(String whereString) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.person where email=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setString(1, whereString);
                return getPerson(con, preparedStatement);
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    private Person getPerson(Connection con, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Person person = entityFamilyReadingFabric.createPerson(resultSet.getInt("id"),
                    resultSet.getString("firstname"),resultSet.getString("lastname"),
                    resultSet.getString("email"));
            con.commit();
            return person;
        }
        throw new SQLException();
    }

    @Override
    public Person read(int id) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String readQuery = "SELECT * FROM entity.person where id=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(readQuery)) {
                preparedStatement.setInt(1, id);
                return getPerson(con, preparedStatement);
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Person readWhere(int indicator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Person> readAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
