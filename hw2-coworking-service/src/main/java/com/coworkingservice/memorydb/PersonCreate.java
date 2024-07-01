package com.coworkingservice.memorydb;

import com.coworkingservice.ConnectionDB;
import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityReadingFabric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonCreate implements Create<Person>{
    private final ReadWhereString<Person> read;
    private final Create<Credential> createCredential;
    private final EntityFamilyFabric entityFamilyFabric;
    public PersonCreate(EntityFamilyFabric entityFamilyFabric) {
        this.read = new PersonRead(new EntityReadingFabric());
        this.createCredential = new CredentialCreate();
        this.entityFamilyFabric = entityFamilyFabric;
    }
    @Override
    public void create(Person person) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            String insertQuery = "INSERT INTO entity.person (firstname, lastname, email) VALUES(?,?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, person.getFirstname());
                preparedStatement.setString(2, person.getLastname());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.executeUpdate();
                con.commit();
                cascadeCreateCredential(person.getEmail());
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Something went wrong, maybe you made a mistake when entering");
            }
        } catch (SQLException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }
    private void cascadeCreateCredential(String email){
        Person person = read.readWhereString(email);
        createCredential.create(entityFamilyFabric.createCredential(person));
    }
}
