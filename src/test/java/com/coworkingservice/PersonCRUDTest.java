package com.coworkingservice;

import com.coworkingservice.entity.Person;

import com.coworkingservice.fabric.*;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.person.PersonCreate;
import com.coworkingservice.memorydb.person.PersonRead;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class PersonCRUDTest extends PropertiesContainerTest {
    private PersonCRUD personCRUD;

    @BeforeEach
    void setUp() {
        this.personCRUD = new PersonCRUD(new PersonCRUDAbstractFabricBaseImp());
    }

    @Test
    @DisplayName("Statement for reading the person by email")
    public void readWhereString() {
        String whereString = "test@test";
        Person person = personCRUD.readWhereString(whereString);
        assertEquals("Map", person.getFirstname());
    }

    @Test
    @DisplayName("Statement for reading the person by id")
    public void read() {
        Person person = personCRUD.read(1);
        assertEquals("Map", person.getFirstname());
    }
}
