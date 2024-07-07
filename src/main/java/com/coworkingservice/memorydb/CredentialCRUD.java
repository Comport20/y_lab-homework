package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.fabric.CredentialCRUDAbstractFabric;

public class CredentialCRUD {
    private final CredentialCRUDAbstractFabric credentialCRUDAbstractFabric;
    public CredentialCRUD(CredentialCRUDAbstractFabric credentialCRUDAbstractFabric){
        this.credentialCRUDAbstractFabric = credentialCRUDAbstractFabric;
    }
    public int readWhereEntity(Credential credential){
        return credentialCRUDAbstractFabric.createReadWhereEntityMechanism().readWhereEntity(credential);
    }
}
