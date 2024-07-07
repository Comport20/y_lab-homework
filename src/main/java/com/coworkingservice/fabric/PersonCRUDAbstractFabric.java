package com.coworkingservice.fabric;

import com.coworkingservice.entity.Person;
import com.coworkingservice.memorydb.Create;
import com.coworkingservice.memorydb.Read;
import com.coworkingservice.memorydb.ReadWhereString;


public interface PersonCRUDAbstractFabric {
    Create<Person> createCreationMechanism();

    ReadWhereString<Person> createReadWhereStringMechanism();

    Read<Person> createReadMechanism();
}
