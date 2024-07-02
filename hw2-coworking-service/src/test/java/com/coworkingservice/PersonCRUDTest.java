package com.coworkingservice;

import com.coworkingservice.entity.Person;

import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.person.PersonCreate;
import com.coworkingservice.memorydb.person.PersonRead;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class PersonCRUDTest extends PropertiesContainerTest {
    private PersonCRUD personCRUD;

    @BeforeEach
    void setUp() {
        EntityFamilyFabric entityFamilyFabric = new EntityFabric();
        EntityFamilyReadingFabric entityFamilyReadingFabric = new EntityReadingFabric();
        Create<Person> personCreate = new PersonCreate(entityFamilyFabric);
        ReadWhereString<Person> readWhereString = new PersonRead(entityFamilyReadingFabric);
        Read<Person> personRead = new PersonRead(entityFamilyReadingFabric);
        this.personCRUD = new PersonCRUD(personCreate, readWhereString, personRead);
    }

    @Test
    public void readWhereString() {
        String whereString = "test@test";
        Person person = personCRUD.readWhereString(whereString);
        assertEquals("Map", person.getFirstname());
    }

    @Test
    public void read() {
        Person person = personCRUD.read(1);
        assertEquals("Map", person.getFirstname());
    }
}
