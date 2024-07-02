package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.fabric.EntityFabric;
import com.coworkingservice.fabric.EntityFamilyFabric;
import com.coworkingservice.fabric.EntityFamilyReadingFabric;
import com.coworkingservice.fabric.EntityReadingFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.credential.CredentialRead;
import com.coworkingservice.memorydb.person.PersonCreate;
import com.coworkingservice.memorydb.person.PersonRead;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CredentialCRUDTest extends PropertiesContainerTest{
    private CredentialCRUD credentialCRUD;
    @BeforeEach
    void setUp() {
        this.credentialCRUD = new CredentialCRUD(new CredentialRead());
    }
    public void readWhereEntity(){
        Credential credential = new Credential("login", "login");
        assertEquals(1,credentialCRUD.readWhereEntity(credential));
        Credential finalCredential = new Credential("none", "none");
        assertThrows(SQLException.class,() -> credentialCRUD.readWhereEntity(finalCredential));
    }
}
