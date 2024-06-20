package com.entity;

public class Tenant extends Person{
    public Tenant(long personId, String firstname, String lastname,String password){
        this.personId = personId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }
}
