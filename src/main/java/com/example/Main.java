package com.example;

import com.example.model.*;
import com.example.util.FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "input.json";
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Phone> phoneList = new ArrayList<>();

    public static void main(String[] args) {
        printHeader();
        
        // Автоматичне завантаження з файлу (з Лаб 9)
        phoneList = FileManager.loadFromJson(FILE_NAME);

        boolean running = true;
        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ (ПРАКТИЧНА №10 - ПОШУК) ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Пошук об’єкта");
            System.out.println("3. Вивести інформацію про всі об’єкти");
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
                    FileManager.saveToJson(phoneList, FILE_NAME);
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 1 до 4.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("=================================================");
        System.out.println("    ПРАКТИЧНА РОБОТА №10 - ПОШУК У КОЛЕКЦІЯХ    ");
        System.out.println("    Пошук за 3 критеріями без Stream API         ");
        System.out.println("=================================================");
    }

    //     ПІДМЕНЮ ПОШУКУ (Вимога 2 та 3)    
    private static void searchMenu() {
        if (phoneList.isEmpty()) {
            System.out.println("\nКолекція порожня! Пошук неможливий.");
            return;
        }

        System.out.println("\n    МЕНЮ ПОШУКУ    ");
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

    //     ОКТРЕМІ МЕТОДИ ПОШУКУ (Вимога 4, 5, 8, 9)    

    // 1. Пошук за брендом
    private static void searchByBrand() {
        String brand = readString("Введіть назву бренду для пошуку: ");
        ArrayList<Phone> results = findByBrand(phoneList, brand);
        printSearchResults(results, "Бренд містить '" + brand + "'");
    }

    public static ArrayList<Phone> findByBrand(ArrayList<Phone> list, String brand) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : list) {
            if (phone.getBrand().equalsIgnoreCase(brand.trim())) {
                result.add(phone);
            }
        }
        return result;
    }

    // 2. Пошук за максимальною ціною (ціна <= maxPrice)
    private static void searchByMaxPrice() {
        double maxPrice = readDouble("Введіть максимальну ціну ($): ");
        ArrayList<Phone> results = findByMaxPrice(phoneList, maxPrice);
        printSearchResults(results, "Ціна <= " + maxPrice + "$");
    }

    public static ArrayList<Phone> findByMaxPrice(ArrayList<Phone> list, double maxPrice) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : list) {
            if (phone.getPrice() <= maxPrice) {
                result.add(phone);
            }
        }
        return result;
    }

    // 3. Пошук за мінімальним ОЗП (ramGb >= minRam)
    private static void searchByMinRam() {
        int minRam = readInt("Введіть мінімальний обсяг ОЗП (ГБ): ");
        ArrayList<Phone> results = findByMinRam(phoneList, minRam);
        printSearchResults(results, "ОЗП >= " + minRam + " ГБ");
    }

    public static ArrayList<Phone> findByMinRam(ArrayList<Phone> list, int minRam) {
        ArrayList<Phone> result = new ArrayList<>();
        for (Phone phone : list) {
            if (phone.getRamGb() >= minRam) {
                result.add(phone);
            }
        }
        return result;
    }

    // Допоміжний метод виводу результатів пошуку
    private static void printSearchResults(ArrayList<Phone> results, String criterion) {
        System.out.println("\n    Результати пошуку за критерієм [" + criterion + "]    ");
        if (results.isEmpty()) {
            System.out.println("Жоден об’єкт не відповідає умовам пошуку.");
        } else {
            System.out.println("Знайдено об’єктів: " + results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    //     РЕШТА МЕТОДІВ (ЗБЕРЕЖЕНІ З ЛАБ 8 ТА 9)    
    private static void createNewObjectMenu() {
        System.out.println("\n    Оберіть тип об’єкта для створення    ");
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

    private static void addBasePhone() {
        System.out.println("\n   Створення Phone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");

        phoneList.add(new Phone(brand, model, price, ram, PhoneType.FEATURE_PHONE));
        System.out.println("Успішно додано базовий телефон!");
    }

    private static void addSmartPhone() {
        System.out.println("\n   Створення SmartPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        String os = readString("Операційна система: ");

        phoneList.add(new SmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os));
        System.out.println("Успішно додано смартфон!");
    }

    private static void addKeypadPhone() {
        System.out.println("\n   Створення KeypadPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        boolean torch = readBoolean("Наявність ліхтарика (1 - Так, 0 - Ні): ");

        phoneList.add(new KeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch));
        System.out.println("Успішно додано кнопковий телефон!");
    }

    private static void addFoldableSmartPhone() {
        System.out.println("\n   Створення FoldableSmartPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        String os = readString("Операційна система: ");
        boolean dualScreen = readBoolean("Чи є додатковий екран (1 - Так, 0 - Ні): ");

        phoneList.add(new FoldableSmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, os, dualScreen));
        System.out.println("Успішно додано гнучкий смартфон!");
    }

    private static void addSecureKeypadPhone() {
        System.out.println("\n   Створення SecureKeypadPhone   ");
        String brand = readString("Бренд: ");
        String model = readString("Модель: ");
        double price = readDouble("Ціна ($): ");
        int ram = readInt("ОЗП (ГБ): ");
        boolean torch = readBoolean("Наявність ліхтарика (1 - Так, 0 - Ні): ");
        String protection = readString("Стандарт захисту (наприклад, IP68): ");

        phoneList.add(new SecureKeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, torch, protection));
        System.out.println("Успішно додано захищений кнопковий телефон!");
    }

    private static void printAllObjects() {
        System.out.println("\n    Список усіх об'єктів у колекції ArrayList<Phone>    ");
        if (phoneList.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            for (int i = 0; i < phoneList.size(); i++) {
                System.out.println((i + 1) + ". " + phoneList.get(i));
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