package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.PersonCRUDAbstractFabric;

;


public class PersonCRUD {
    private final PersonCRUDAbstractFabric personCRUDFabric;
    public PersonCRUD(PersonCRUDAbstractFabric personCRUDFabric) {
        this.personCRUDFabric = personCRUDFabric;
    }

    public void create(Person person) {
        personCRUDFabric.createCreationMechanism().create(person);
    }

    public Person readWhereString(String whereString){
        return personCRUDFabric.createReadWhereStringMechanism().readWhereString(whereString);
    }
    public Person read(int id) {
        return personCRUDFabric.createReadMechanism().read(id);
    }
}
