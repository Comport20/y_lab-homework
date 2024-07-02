package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.credential.CredentialRead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CredentialCRUDTest extends PropertiesContainerTest{
    private CredentialCRUD credentialCRUD;
    @BeforeEach
    void setUp() {
        this.credentialCRUD = new CredentialCRUD(new CredentialRead());
    }
    @Test
    public void readWhereEntity(){
        Credential credential = new Credential("login", "login");
        assertEquals(1,credentialCRUD.readWhereEntity(credential));
        Credential finalCredential = new Credential("none", "none");
        assertEquals(-1, credentialCRUD.readWhereEntity(finalCredential));
    }
}
