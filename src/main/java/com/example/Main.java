package com.example;

import com.example.model.*;
import com.example.util.FileManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final String FILE_NAME = "input.json";
    private static final Scanner scanner = new Scanner(System.in);
    private static Store store;

    public static void main(String[] args) {
        printHeader();
        store = FileManager.loadFromJson(FILE_NAME, "TechStore");

        boolean running = true;
        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ (ЛАБОРАТОРНА №17 - UPDATE & DELETE) ===");
            System.out.println("1. Додати товар у магазин");
            System.out.println("2. Пошук товару в магазині");
            System.out.println("3. Модифікувати товар");
            System.out.println("4. Видалити товар");
            System.out.println("5. Вивести асортимент магазину");
            System.out.println("6. Вивести відсортовану інформацію про всі товари");
            System.out.println("0. Завершити роботу програми");
            System.out.print("Оберіть пункт (0-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu();
                    break;
                case "2":
                    searchMenu();
                    break;
                case "3":
                    modifyObjectMenu();
                    break;
                case "4":
                    deleteObjectMenu();
                    break;
                case "5":
                    printAllObjects();
                    break;
                case "6":
                    printSortedObjects();
                    break;
                case "0":
                    running = false;
                    FileManager.saveToJson(store, FILE_NAME);
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 0 до 6.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("=================================================");
        System.out.println(" ЛАБОРАТОРНА РОБОТА №17 - Modification & Deletion");
        System.out.println("   Модифікація та видалення об'єктів у колекції  ");
        System.out.println("=================================================");
    }

    private static void modifyObjectMenu() {
        if (store.isEmpty()) {
            System.out.println("\nМагазин порожній. Модифікація неможлива.");
            return;
        }

        System.out.println("\n    Оберіть товар для модифікації    ");
        ArrayList<StoreItem> items = store.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        System.out.println("0. Повернутися до головного меню");

        int index = readInt("Ваш вибір (0-" + items.size() + "): ");
        if (index == 0) {
            System.out.println("Модифікацію скасовано.");
            return;
        }
        if (index < 1 || index > items.size()) {
            System.out.println("Помилка: об'єкт із таким номером не знайдено.");
            return;
        }

        Phone oldPhone = items.get(index - 1).getPhone();

        String brand = oldPhone.getBrand();
        String model = oldPhone.getModel();
        double price = oldPhone.getPrice();
        int ram = oldPhone.getRamGb();

        System.out.println("\n    Оберіть атрибут для зміни    ");
        System.out.println("1. Бренд (поточний: " + brand + ")");
        System.out.println("2. Модель (поточна: " + model + ")");
        System.out.println("3. Ціна (поточна: " + price + "$)");
        System.out.println("4. Обсяг ОЗП (поточний: " + ram + " ГБ)");
        System.out.println("0. Скасувати");

        int attrChoice = readInt("Ваш вибір (0-4): ");
        if (attrChoice == 0) {
            System.out.println("Модифікацію скасовано.");
            return;
        }

        try {
            switch (attrChoice) {
                case 1:
                    brand = readString("Введіть новий бренд: ");
                    break;
                case 2:
                    model = readString("Введіть нову модель: ");
                    break;
                case 3:
                    price = readDouble("Введіть нову ціну ($): ");
                    break;
                case 4:
                    ram = readInt("Введіть новий обсяг ОЗП (ГБ): ");
                    break;
                default:
                    System.out.println("Некоректний вибір атрибута.");
                    return;
            }

            Phone updatedPhone;
            if (oldPhone instanceof FoldableSmartPhone) {
                FoldableSmartPhone f = (FoldableSmartPhone) oldPhone;
                updatedPhone = new FoldableSmartPhone(brand, model, price, ram, f.getType(), f.getOperatingSystem(), f.isDualScreen());
            } else if (oldPhone instanceof SmartPhone) {
                SmartPhone s = (SmartPhone) oldPhone;
                updatedPhone = new SmartPhone(brand, model, price, ram, s.getType(), s.getOperatingSystem());
            } else if (oldPhone instanceof SecureKeypadPhone) {
                SecureKeypadPhone sec = (SecureKeypadPhone) oldPhone;
                updatedPhone = new SecureKeypadPhone(brand, model, price, ram, sec.getType(), sec.isHasTorch(), sec.getProtectionStandard());
            } else if (oldPhone instanceof KeypadPhone) {
                KeypadPhone k = (KeypadPhone) oldPhone;
                updatedPhone = new KeypadPhone(brand, model, price, ram, k.getType(), k.isHasTorch());
            } else {
                System.out.println("Помилка: невідомий тип об'єкта.");
                return;
            }

            boolean updated = store.update(oldPhone, updatedPhone);
            if (updated) {
                FileManager.saveToJson(store, FILE_NAME);
                System.out.println("Товар успішно модифіковано!");
            } else {
                System.out.println("Помилка: об'єкт не знайдено в магазині.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка модифікації: " + e.getMessage());
        }
    }

    private static void deleteObjectMenu() {
        if (store.isEmpty()) {
            System.out.println("\nМагазин порожній. Видалення неможливе.");
            return;
        }

        System.out.println("\n    Оберіть товар для видалення    ");
        ArrayList<StoreItem> items = store.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        System.out.println("0. Повернутися до головного меню");

        int index = readInt("Ваш вибір (0-" + items.size() + "): ");
        if (index == 0) {
            System.out.println("Видалення скасовано.");
            return;
        }
        if (index < 1 || index > items.size()) {
            System.out.println("Помилка: об'єкт із таким номером не знайдено.");
            return;
        }

        Phone targetPhone = items.get(index - 1).getPhone();
        boolean confirm = readBoolean("Підтвердити видалення '" + targetPhone.getBrand() + " " + targetPhone.getModel() + "'? (1 - Так, 0 - Ні): ");

        if (confirm) {
            boolean deleted = store.delete(targetPhone);
            if (deleted) {
                FileManager.saveToJson(store, FILE_NAME);
                System.out.println("Товар успішно видалено з магазину!");
            } else {
                System.out.println("Помилка: об'єкт не знайдено.");
            }
        } else {
            System.out.println("Видалення скасовано користувачем.");
        }
    }

    private static void printSortedObjects() {
        if (store.isEmpty()) {
            System.out.println("\nМагазин порожній. Сортування неможливе.");
            return;
        }

        System.out.println("\n    Оберіть критерій сортування    ");
        System.out.println("1. Сортувати за ціною (від дешевших до дорожчих)");
        System.out.println("2. Сортувати за обсягом ОЗП (від більшого до меншого)");
        System.out.println("3. Сортувати за брендом (за алфавітом)");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Ваш вибір (0-3): ");

        String choice = scanner.nextLine().trim();
        ArrayList<StoreItem> sortedItems = null;

        switch (choice) {
            case "1":
                sortedItems = store.getItemsSortedByPrice();
                System.out.println("\n    Відсортовано за ціною (зростання)    ");
                break;
            case "2":
                sortedItems = store.getItemsSortedByRamDesc();
                System.out.println("\n    Відсортовано за обсягом ОЗП (спадання)    ");
                break;
            case "3":
                sortedItems = store.getItemsSortedByBrand();
                System.out.println("\n    Відсортовано за брендом (алфавіт)    ");
                break;
            case "0":
                System.out.println("Повернення до головного меню.");
                return;
            default:
                System.out.println("Некоректний вибір критерію сортування.");
                return;
        }

        for (int i = 0; i < sortedItems.size(); i++) {
            System.out.println((i + 1) + ". " + sortedItems.get(i));
        }
    }

    private static void searchMenu() {
        if (store.isEmpty()) {
            System.out.println("\nМагазин порожній! Пошук неможливий.");
            return;
        }

        System.out.println("\n    МЕНЮ ПОШУКУ В МАГАЗИНІ    ");
        System.out.println("1. Пошук за брендом");
        System.out.println("2. Пошук за максимальною ціною");
        System.out.println("3. Пошук за мінімальним обсягом ОЗП (RAM)");
        System.out.println("4. Пошук за UUID");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Оберіть критерій пошуку (0-4): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                searchByBrand();
                break;
            case "2":
                searchByMaxPrice();
                break;
            case "3":
                searchByMinRam();
                break;
            case "4":
                searchByUuid();
                break;
            case "0":
                System.out.println("Повернення до головного меню.");
                break;
            default:
                System.out.println("Некоректний вибір критерію пошуку.");
        }
    }

    private static void searchByBrand() {
        String brand = readString("Введіть назву бренду для пошуку: ");
        ArrayList<StoreItem> results = store.findByBrand(brand);
        printSearchResults(results, "Бренд містить '" + brand + "'");
    }

    private static void searchByMaxPrice() {
        double maxPrice = readDouble("Введіть максимальну ціну ($): ");
        ArrayList<StoreItem> results = store.findByMaxPrice(maxPrice);
        printSearchResults(results, "Ціна <= " + maxPrice + "$");
    }

    private static void searchByMinRam() {
        int minRam = readInt("Введіть мінімальний обсяг ОЗП (ГБ): ");
        ArrayList<StoreItem> results = store.findByMinRam(minRam);
        printSearchResults(results, "ОЗП >= " + minRam + " ГБ");
    }

    private static void searchByUuid() {
        String rawUuid = readString("Введіть UUID товару: ");
        try {
            UUID parsedUuid = UUID.fromString(rawUuid);
            StoreItem found = store.findByUuid(parsedUuid);
            if (found != null) {
                System.out.println("\nЗнайдено об'єкт:");
                System.out.println(found);
            } else {
                System.out.println("Об'єкт із вказаним UUID не знайдено.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: некоректний формат UUID.");
        }
    }

    private static void printSearchResults(ArrayList<StoreItem> results, String criterion) {
        System.out.println("\n  - Результати пошуку в магазині [" + criterion + "]   -");
        if (results.isEmpty()) {
            System.out.println("Жоден товар не відповідає умовам пошуку.");
        } else {
            System.out.println("Знайдено позицій: " + results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    private static void createNewObjectMenu() {
        System.out.println("\n    Оберіть тип товару для додавання    ");
        System.out.println("1. Смартфон (SmartPhone)");
        System.out.println("2. Кнопковий телефон (KeypadPhone)");
        System.out.println("3. Гнучкий смартфон (FoldableSmartPhone)");
        System.out.println("4. Захищений кнопковий телефон (SecureKeypadPhone)");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Ваш вибір (0-4): ");

        String choice = scanner.nextLine().trim();

        try {
            switch (choice) {
                case "1":
                    addSmartPhone();
                    break;
                case "2":
                    addKeypadPhone();
                    break;
                case "3":
                    addFoldableSmartPhone();
                    break;
                case "4":
                    addSecureKeypadPhone();
                    break;
                case "0":
                    System.out.println("Повернення до головного меню.");
                    break;
                default:
                    System.out.println("Некоректний вибір типу об'єкта.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    private static void addSmartPhone() {
        System.out.println("\n   Створення SmartPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        String os = readString("Операційна система: ");
        int qty = readInt("Кількість шт.: ");

        store.addNewPhone(new SmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os), qty);
        System.out.println("Товар успішно додано у магазин!");
    }

    private static void addKeypadPhone() {
        System.out.println("\n   Створення KeypadPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        boolean torch = readBoolean("Наявність ліхтарика (1 - Так, 0 - Ні): ");
        int qty = readInt("Кількість шт.: ");

        store.addNewPhone(new KeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch), qty);
        System.out.println("Товар успішно додано у магазин!");
    }

    private static void addFoldableSmartPhone() {
        System.out.println("\n   Створення FoldableSmartPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        String os = readString("Операційна система: ");
        boolean dualScreen = readBoolean("Чи є додатковий екран (1 - Так, 0 - Ні): ");
        int qty = readInt("Кількість шт.: ");

        store.addNewPhone(new FoldableSmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os, dualScreen), qty);
        System.out.println("Товар успішно додано у магазин!");
    }

    private static void addSecureKeypadPhone() {
        System.out.println("\n   Створення SecureKeypadPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        boolean torch = readBoolean("Наявність ліхтарика (1 - Так, 0 - Ні): ");
        String protection = readString("Стандарт захисту (наприклад, IP68): ");
        int qty = readInt("Кількість шт.: ");

        store.addNewPhone(new SecureKeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch, protection), qty);
        System.out.println("Товар успішно додано у магазин!");
    }

    private static void printAllObjects() {
        System.out.println("\n    Асортимент магазину '" + store.getName() + "'   -");
        if (store.isEmpty()) {
            System.out.println("Магазин порожній.");
        } else {
            ArrayList<StoreItem> items = store.getItems();
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть дійсне число.");
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    private static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if ("1".equals(input) || "true".equalsIgnoreCase(input)) return true;
            if ("0".equals(input) || "false".equalsIgnoreCase(input)) return false;
            System.out.println("Помилка: введіть 1 (Так) або 0 (Ні).");
        }
    }
}