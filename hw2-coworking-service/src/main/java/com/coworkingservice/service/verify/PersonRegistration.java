package com.coworkingservice.service.verify;


import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.MemoryDB;

import java.util.Map;

public class PersonRegistration implements Registration {
    private final Credential credential;
    private final Map<Credential, Person> personMapTable;
    private EntityFamilyFabric entityFabric;
    public PersonRegistration(Credential credential, EntityFamilyFabric entityFabric) {
        this.credential = credential;
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
        this.entityFabric = entityFabric;
    }
    @Override
    public boolean register() {
        if(personMapTable.containsKey(credential)) {
            return false;
        }
        personMapTable.put(credential, entityFabric.createPerson());
        return true;
    }
}
