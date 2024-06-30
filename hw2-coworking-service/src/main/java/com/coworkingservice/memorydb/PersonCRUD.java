package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Person;

;


public class PersonCRUD {
    private final Create<Person> personCreate;
    private final ReadWhereString<Person> readWhereString;
    private final Read<Person> personRead;
    public PersonCRUD(Create<Person> personCreate, ReadWhereString<Person> readWhereString,Read<Person> personRead) {
        this.personCreate = personCreate;
        this.readWhereString = readWhereString;
        this.personRead = personRead;
    }

    public void create(Person person) {
        personCreate.create(person);
    }

    public Person readWhereString(String whereString){
        return readWhereString.readWhereString(whereString);
    }
    public Person read(int id) {
        return personRead.read(id);
    }
}
