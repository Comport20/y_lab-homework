package com.coworkingservice.service.verify;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.memorydb.MemoryDB;

import java.util.Map;

public class PersonAuthentication implements Authentication<Person> {
    private final Credential credential;
    private final Map<Credential, Person> personMapTable;
    public PersonAuthentication(Credential credential) {
        this.credential = credential;
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
    }
    @Override
    public Person authenticate() {
        if(personMapTable.containsKey(credential))
            return personMapTable.get(credential);
        return null;
    }
}
