package com.coworkingservice.fabric;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Tenant;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Scanner;

public class TemporaryFabric {
    private Scanner scanner;
    public TemporaryFabric() {
        scanner = ScannerSingleton.getInstance().getScanner();
    }
    public Credential createCredential() {
        System.out.printf("%-10s", "Введите логин: ");
        String login = scanner.next();
        System.out.printf("%-10s", "Введите Пароль: ");
        String password = scanner.next();
        return new Credential(login,password);
    }

    public Person createPerson() {
        System.out.printf("%-20s", "Введите имя: ");
        String firstname = scanner.next();
        System.out.printf("%-20s", "Введите фамилию: ");
        String lastname = scanner.next();
        return new Tenant(firstname, lastname);
    }
}
