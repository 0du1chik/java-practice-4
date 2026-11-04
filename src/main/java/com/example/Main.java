package com.example;

import com.example.model.*;
import com.example.util.FileManager;
import com.example.util.PhoneRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "input.json";
    private static final Scanner scanner = new Scanner(System.in);
    private static Store store;
    private static PhoneRepository phoneRepository;

    public static void main(String[] args) {
        printHeader();
        // Читання шляху до файлу конфігурації БД з args[0]
        if (args.length > 0) {
            phoneRepository = new PhoneRepository(args[0]);
        } else {
            System.out.println("Увага: Шлях до db.properties не передано в args[0]. Робота без збереження в БД.");
        }

        store = FileManager.loadFromJson(FILE_NAME, "TechStore");

        boolean running = true;
        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ (ПРАКТИЧНА №12 - JDBC) ===");
            System.out.println("1. Додати товар у магазин (та зберегти в БД)");
            System.out.println("2. Пошук товару в магазині");
            System.out.println("3. Вивести асортимент магазину");
            System.out.println("4. Завершити роботу програми");
            System.out.print("Оберіть пункт (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu();
                    break;
                case "2":
                    searchMenu();
                    break;
                case "3":
                    printAllObjects();
                    break;
                case "4":
                    running = false;
                    FileManager.saveToJson(store, FILE_NAME);
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 1 до 4.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("=================================================");
        System.out.println("    ПРАКТИЧНА РОБОТА №12 - ІНТЕГРАЦІЯ З JDBC    ");
        System.out.println("    Збереження в БД через PreparedStatement      ");
        System.out.println("=================================================");
    }

    private static void saveToDatabaseIfConfigured(Phone phone) {
        if (phoneRepository != null) {
            phoneRepository.save(phone);
        }
    }

    // МОДИФІКОВАНІ МЕТОДИ СТВОРЕННЯ З ВИКЛИКОМ JDBC

    private static void addBasePhone() {
        System.out.println("\n   Створення Phone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        int qty = readInt("Кількість шт.: ");

        Phone phone = new Phone(brand, model, price, ram, PhoneType.FEATURE_PHONE);
        store.addNewPhone(phone, qty);
        saveToDatabaseIfConfigured(phone);
        System.out.println("Товар успішно додано у магазин!");
    }

    private static void addSmartPhone() {
        System.out.println("\n   Створення SmartPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        String os = readString("Операційна система: ");
        int qty = readInt("Кількість шт.: ");

        SmartPhone phone = new SmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os);
        store.addNewPhone(phone, qty);
        saveToDatabaseIfConfigured(phone);
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

        KeypadPhone phone = new KeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch);
        store.addNewPhone(phone, qty);
        saveToDatabaseIfConfigured(phone);
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

        FoldableSmartPhone phone = new FoldableSmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os, dualScreen);
        store.addNewPhone(phone, qty);
        saveToDatabaseIfConfigured(phone);
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

        SecureKeypadPhone phone = new SecureKeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch, protection);
        store.addNewPhone(phone, qty);
        saveToDatabaseIfConfigured(phone);
        System.out.println("Товар успішно додано у магазин!");
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
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Оберіть критерій пошуку (0-3): ");

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

    private static void printSearchResults(ArrayList<StoreItem> results, String criterion) {
        System.out.println("\n    Результати пошуку в магазині [" + criterion + "]    ");
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
        System.out.println("1. Базовий телефон (Phone)");
        System.out.println("2. Смартфон (SmartPhone)");
        System.out.println("3. Кнопковий телефон (KeypadPhone)");
        System.out.println("4. Гнучкий смартфон (FoldableSmartPhone)");
        System.out.println("5. Захищений кнопковий телефон (SecureKeypadPhone)");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Ваш вибір (0-5): ");

        String choice = scanner.nextLine().trim();

        try {
            switch (choice) {
                case "1":
                    addBasePhone();
                    break;
                case "2":
                    addSmartPhone();
                    break;
                case "3":
                    addKeypadPhone();
                    break;
                case "4":
                    addFoldableSmartPhone();
                    break;
                case "5":
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

    private static void printAllObjects() {
        System.out.println("\n   Асортимент магазину '" + store.getName() + "'   -");
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