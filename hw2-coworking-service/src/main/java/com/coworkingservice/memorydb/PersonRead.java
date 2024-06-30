package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Person;

import java.util.List;

public class PersonRead implements Read<Person>{
    @Override
    public Person read(int id) {
        return null;
    }

    @Override
    public Person readWhere(int indicator) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        return List.of();
    }
}
