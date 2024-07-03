package com.coworkingservice.entity;

public class Tenant extends Person{
    public Tenant(String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public Tenant(int id, String firstname, String lastname,String email){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
