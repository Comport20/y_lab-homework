package com.coworkingservice.service;

import com.coworkingservice.ConnectionDB;

import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    private final Scanner scanner;
    public ScannerSingleton() {
        this.scanner = new Scanner(System.in);
    }
    public static ScannerSingleton getInstance() {
        if (instance == null) {
            synchronized (ConnectionDB.class) {
                if (instance == null) {
                    instance = new ScannerSingleton();
                }
            }
        }
        return instance;
    }
    public Scanner getScanner() {
        return scanner;
    }
}
