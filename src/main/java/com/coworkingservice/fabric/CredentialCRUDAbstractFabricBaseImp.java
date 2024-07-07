package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.memorydb.credential.CredentialRead;

public class CredentialCRUDAbstractFabricBaseImp implements CredentialCRUDAbstractFabric {
    @Override
    public ReadWhereEntity<Credential> createReadWhereEntityMechanism() {
        return new CredentialRead();
    }
}
