package com.coworkingservice;


import com.coworkingservice.service.ScannerSingleton;


public class CoworkingService {
    public static void main(String[] args) {
        CoworkingFacade coworkingFacade = new CoworkingFacade();
        coworkingFacade.start();
        ScannerSingleton.getInstance().getScanner().close();
    }
}
