package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.fabric.CredentialCRUDAbstractFabricBaseImp;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.credential.CredentialRead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CredentialDTOCRUDTest extends PropertiesContainerTest{
    private CredentialCRUD credentialCRUD;
    @BeforeEach
    void setUp() {
        this.credentialCRUD = new CredentialCRUD(new CredentialCRUDAbstractFabricBaseImp());
    }
    @Test
    @DisplayName("Statement for reading the credential by login and password")
    public void readWhereEntity(){
        Credential credential = new Credential("login", "login");
        assertEquals(1,credentialCRUD.readWhereEntity(credential));
        Credential finalCredential = new Credential("none", "none");
        assertEquals(-1, credentialCRUD.readWhereEntity(finalCredential));
    }
}
