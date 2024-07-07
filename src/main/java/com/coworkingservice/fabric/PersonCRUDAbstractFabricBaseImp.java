package com.coworkingservice.fabric;

import com.coworkingservice.entity.Person;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereString;
import com.coworkingservice.memorydb.person.PersonCreate;
import com.coworkingservice.memorydb.person.PersonRead;

public class PersonCRUDAbstractFabricBaseImp implements PersonCRUDAbstractFabric{
    @Override
    public Create<Person> createCreationMechanism() {
        return new PersonCreate(new EntityFabric());
    }

    @Override
    public ReadWhereString<Person> createReadWhereStringMechanism() {
        return new PersonRead(new EntityReadingFabric());
    }

    @Override
    public Read<Person> createReadMechanism() {
        return new PersonRead(new EntityReadingFabric());
    }
}
