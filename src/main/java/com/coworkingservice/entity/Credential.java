package com.coworkingservice.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Credential {
    private String username;
    private String password;
    private Person person;
    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public int getPersonId(){
        return person.getId();
    }
}
