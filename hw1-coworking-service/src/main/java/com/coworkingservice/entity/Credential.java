package com.coworkingservice.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Credential {
    private String username;
    private String password;
    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
