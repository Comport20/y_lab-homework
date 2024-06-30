package com.coworkingservice.service.verify;


import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.ConnectionDB;

import java.util.Map;

public class PersonRegistration implements Registration {
    private final Credential credential;
    private final Map<Credential, Person> personMapTable;
    private final EntityFamilyFabric entityFabric;
    public PersonRegistration(Credential credential, EntityFamilyFabric entityFabric) {
        this.credential = credential;
        this.personMapTable = ConnectionDB.getInstance().getPersonMapTable();
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
