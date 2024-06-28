package com.coworkingservice.memorydb;

import com.coworkingservice.MemoryDB;
import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;

import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.service.verify.Registration;
import com.coworkingservice.service.verify.PersonRegistration;


import java.util.Map;


public class PersonCRUD {
    private Map<Credential, Person> personMapTable;
    private Registration registration;
    private EntityFamilyFabric entityFabric;

    public PersonCRUD(EntityFamilyFabric entityFabric) {
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
        this.entityFabric = entityFabric;
    }


    public void create(Credential credential) {
        this.registration = new PersonRegistration(credential, entityFabric);
        if (registration.register()) {
            System.out.println("The user has been successfully registered.");
        } else {
            System.out.println("Such a user already exists.");
        }
    }


    public Person read(Credential key) {
        return personMapTable.get(key);
    }


    public void readAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void update(Credential key) {
        if (personMapTable.containsKey(key)) {
            personMapTable.put(key, entityFabric.createPerson());
        }
    }


    public void delete(Credential Key) {
        personMapTable.remove(Key);
    }
}
