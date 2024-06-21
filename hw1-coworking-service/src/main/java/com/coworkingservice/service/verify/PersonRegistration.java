package com.coworkingservice.service.verify;


import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.memorydb.MemoryDB;

import java.util.Map;

public class PersonRegistration implements Registration {
    private final Credential credential;
    private final Person person;
    private final Map<Credential, Person> personMapTable;
    public PersonRegistration(Credential credential, Person person) {
        this.credential = credential;
        this.person = person;
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
    }
    @Override
    public boolean register() {
        if(personMapTable.containsKey(credential)) {
            return false;
        }
        personMapTable.put(credential, person);
        return true;
    }
}
