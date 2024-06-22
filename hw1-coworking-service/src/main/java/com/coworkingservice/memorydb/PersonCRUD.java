package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;

import com.coworkingservice.fabric.TemporaryFabric;
import com.coworkingservice.service.verify.Registration;
import com.coworkingservice.service.verify.PersonRegistration;


import java.util.Map;


public class PersonCRUD  {
    private Map<Credential, Person> personMapTable;
    private Registration registration;
    private TemporaryFabric temporaryFabric;
    public PersonCRUD() {
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
        this.temporaryFabric = new TemporaryFabric();
    }


    public void create(Credential credential) {
        if (!personMapTable.containsKey(credential)) {
            Person tenant = temporaryFabric.createPerson();
            this.registration = new PersonRegistration(credential, tenant);
            if (registration.register()) {
                System.out.println("The user has been successfully registered.");
            } else {
                System.out.println("Something went wrong, try again or contact support.");
            }
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
            Person tenant = temporaryFabric.createPerson();
            personMapTable.put(key, tenant);
        }
    }


    public void delete(Credential Key) {
        personMapTable.remove(Key);
    }
}
