package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.memorydb.*;

public interface CredentialCRUDAbstractFabric {

    ReadWhereEntity<Credential> createReadWhereEntityMechanism();

}
