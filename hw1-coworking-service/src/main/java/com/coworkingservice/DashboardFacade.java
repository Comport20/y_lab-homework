package com.coworkingservice;

import com.coworkingservice.entity.Credential;
import com.coworkingservice.entity.Person;
import com.coworkingservice.entity.Room;
import com.coworkingservice.fabric.TemporaryFabric;
import com.coworkingservice.memorydb.*;
import com.coworkingservice.service.ScannerSingleton;
import com.coworkingservice.service.filter.PersonFilter;
import com.coworkingservice.service.filter.PriceFilter;
import com.coworkingservice.service.filter.TimeFilter;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Scanner;


public class DashboardFacade {

    private final FreeSlotsCRUD freeSlotsCRUD;
    private final PersonCRUD personCRUD;
    private final ReservedSlots reservedSlots;
    private final RoomCRUD roomCRUD;
    private final Scanner scanner;
    private Person person;
    private boolean auth = false;
    private boolean exit = false;
    private final TemporaryFabric temporaryFabric;

    public DashboardFacade(FreeSlotsCRUD freeSlotsCRUD, PersonCRUD personCRUD, ReservedSlots reservedSlots, RoomCRUD roomCRUD, TemporaryFabric temporaryFabric) {
        this.freeSlotsCRUD = freeSlotsCRUD;
        this.personCRUD = personCRUD;
        this.reservedSlots = reservedSlots;
        this.roomCRUD = roomCRUD;
        this.scanner = ScannerSingleton.getInstance().getScanner();
        this.temporaryFabric = temporaryFabric;
    }

    public void startCoworkingService() {
        while (!exit) {
            if (!auth) {
                System.out.println("Здравствуйте!\n");
                System.out.printf("%-20s\n", "Для входа нажмите 1");
                System.out.printf("%-20s\n", "Для регистрации нажмите 2");
                System.out.printf("%-20s\n", "Для выхода нажмите 0");
                switch (scanner.nextInt()) {
                    case 0 -> exit = true;
                    case 1 -> auth();
                    case 2 -> registration();
                    default -> System.out.println("Ошибка ввода попробуйте еще раз");
                }
            } else {
                System.out.printf("%-60s\n", "Для просмотра рабочих мест и конференц-залов нажмите 1");
                System.out.printf("%-60s\n", "Для просмотра доступного бронирования 2");
                System.out.printf("%-60s\n", "Для просмотра всех бронирований нажмите 3");
                System.out.printf("%-60s\n", "Для выхода из учетной записи нажмите 6");
                System.out.printf("%-20s\n", "Для выхода нажмите 0");
                switch (scanner.nextInt()) {
                    case 0 -> exit = true;
                    case 1 -> reviewPlaces();
                    case 2 -> availableSlots();
                    case 3 -> reservedPlaces();
                    case 6 -> auth = false;
                    default -> System.out.println("Ошибка ввода попробуйте еще раз");
                }
            }
        }
    }

    private void auth() {
        person = personCRUD.read(temporaryFabric.createCredential());
        if (person != null) {
            System.out.println("Пользователь успешно авторизирован");
            auth = true;
        }else {
            System.out.println("Данный пользоваель не найден");
        }
    }

    private void registration() {
        Credential credential = temporaryFabric.createCredential();
        personCRUD.create(credential);
        person = personCRUD.read(credential);
        auth = true;
    }

    private void reviewPlaces() {
        boolean isEditEnd = false;
        while (!isEditEnd) {
            roomCRUD.readAll();
            System.out.println("Для добавления аудитории нажмите 1");
            System.out.println("Для измнения аудитории нажмите 2");
            System.out.println("Для удаления аудитории нажмите 3");
            System.out.println("Назад - 0");
            switch (scanner.nextInt()) {
                case 0 -> isEditEnd = true;
                case 1 -> roomCRUD.create();
                case 2 -> {
                    System.out.println("Введите номер аудитории которую хотите изменить");
                    Room room = roomCRUD.read(scanner.nextLong());
                    if (room != null)
                        roomCRUD.update(room.getRoomId());
                    else
                        System.out.println("Номер введ некорректно");
                }
                case 3 -> {
                    System.out.println("Введите номер аудитории которую хотите удалить");
                    Room room = roomCRUD.read(scanner.nextLong());
                    if (room != null)
                        roomCRUD.delete(room.getRoomId());
                    else
                        System.out.println("Номер введ некорректно");
                }
            }
        }
    }

    private void availableSlots() {
        roomCRUD.readAll();
        System.out.println("Выберите аудиторию для бронирования: ");
        long roomId = scanner.nextLong();
        System.out.println("Какое день для бронирования вас интересует(формат для ввода YYYY-MM-DD): ");
        String date = scanner.next();
        try {
            LocalDate localDate = LocalDate.parse(date);
            freeSlotsCRUD.readAll(roomId, person, localDate);
        } catch (Exception e) {
            System.out.println("Что то пошло не так, убедитесь, что правильно ввели дату");
        }
    }

    private void reservedPlaces() {
        boolean reservedPlacesExit = false;
        while (!reservedPlacesExit) {
            reservedSlots.readAll();
            System.out.println("Для отмены бронирование нажмите 1");
            System.out.println("Для сортировки по дате нажмите 2");
            System.out.println("Для сортировки по ресурсу нажмите 3");
            System.out.println("Для сортировки по пользователю нажмите 4");
            System.out.println("Назад - 0");
            switch (scanner.nextInt()) {
                case 0 -> reservedPlacesExit = true;
                case 1 -> undoneBooking();
                case 2 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new TimeFilter());
                case 3 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new PriceFilter());
                case 4 -> MemoryDB.getInstance().getReservedSlotListTable().sort(new PersonFilter());
            }
        }
    }

    private void undoneBooking() {
        System.out.println("Введит номер аудитории: ");
        long roomId = scanner.nextLong();
        System.out.println("Введит время формата YYYY-MM-DDThh:mm:ss с котрого начинается вша бронь: ");
        String localDateTimeStr = scanner.next();
        try {
            LocalDateTime fromLocalDateTime = LocalDateTime.parse(localDateTimeStr);
            reservedSlots.delete(roomId, fromLocalDateTime);
        } catch (Exception e) {
            System.out.println("Что то пошло не так, убедитесь, что правильно ввели дату и время");
            e.printStackTrace();
        }
    }
}
