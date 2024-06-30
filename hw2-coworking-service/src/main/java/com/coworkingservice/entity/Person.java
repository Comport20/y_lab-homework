package com.coworkingservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Person {
    @Setter(AccessLevel.NONE)
    protected int id;
    protected String firstname;
    protected String lastname;
}
