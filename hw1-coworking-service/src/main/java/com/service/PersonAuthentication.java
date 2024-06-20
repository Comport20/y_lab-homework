package com.service;

public class PersonAuthentication implements Authentication {
    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }
}
