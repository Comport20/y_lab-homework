package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Credential;

public class CredentialCRUD {
    private final ReadWhereEntity<Credential> readWhereEntity;
    public CredentialCRUD(ReadWhereEntity<Credential> readWhereEntity){
        this.readWhereEntity = readWhereEntity;
    }
    public int readWhereEntity(Credential credential){
        return readWhereEntity.readWhereEntity(credential);
    }

}
