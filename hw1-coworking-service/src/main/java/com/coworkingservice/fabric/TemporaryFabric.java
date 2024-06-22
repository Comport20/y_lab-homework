package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Tenant;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Scanner;

public class TemporaryFabric {
    private final Scanner scanner;
    public TemporaryFabric() {
        scanner = ScannerSingleton.getInstance().getScanner();
    }
    public Credential createCredential() {
        System.out.printf("%-20s", "Enter your username: ");
        String login = scanner.next();
        System.out.printf("%-20s", "Enter the Password: ");
        String password = scanner.next();
        return new Credential(login,password);
    }

    public Person createPerson() {
        System.out.printf("%-20s", "Enter your first name: ");
        String firstname = scanner.next();
        System.out.printf("%-20s", "Enter your last name: ");
        String lastname = scanner.next();
        return new Tenant(firstname, lastname);
    }
}
