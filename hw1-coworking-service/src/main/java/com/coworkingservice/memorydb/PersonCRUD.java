package com.coworkingservice.memorydb;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Tenant;
import com.coworkingservice.service.verify.Registration;
import com.coworkingservice.service.verify.PersonRegistration;
import com.coworkingservice.service.ScannerSingleton;

import java.util.Map;
import java.util.Scanner;

public class PersonCRUD implements CRUD<Credential> {
    private Map<Credential, Person> personMapTable;
    private Registration registration;
    private Scanner scanner;
    public PersonCRUD() {
        scanner = ScannerSingleton.getInstance().getScanner();
        this.personMapTable = MemoryDB.getInstance().getPersonMapTable();
    }

    @Override
    public void create() {
        Credential credential = createCredential();
        if (!personMapTable.containsKey(credential)) {
            Person tenant = createPerson();
            this.registration = new PersonRegistration(credential, tenant);
            if (registration.register()) {
                System.out.println("Пользователь успешно зарегистрирован.");
            } else {
                System.out.println("Что-то пошло не так, попробйте еще раз или обратиесь в поддержку.");
            }
        } else {
            System.out.println("Такой пользователь уже существует.");
        }
    }

    @Override
    public void read(Credential key) {

    }

    @Override
    public void readAll() {
        throw new UnsupportedOperationException("Данная операция не поддерживается в целя конфиденциальность");
    }

    @Override
    public void update(Credential key) {
        if (personMapTable.containsKey(key)) {
            Person tenant = createPerson();
            personMapTable.put(key, tenant);
        }
    }

    @Override
    public void delete(Credential Key) {
        personMapTable.remove(Key);
    }

    private Credential createCredential() {
        System.out.printf("%-20s", "Введите логин: ");
        String login = scanner.nextLine();
        System.out.printf("\n%-20s", "Введите Пароль: ");
        String password = scanner.nextLine();
        return new Credential(login,password);
    }

    private Person createPerson() {
        System.out.printf("%-20s", "Введите имя: ");
        String firstname = scanner.nextLine();
        System.out.printf("\n%-20s", "Введите фамилию: ");
        String lastname = scanner.nextLine();
        return new Tenant(firstname, lastname);
    }
}
