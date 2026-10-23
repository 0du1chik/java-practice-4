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
        
        // Автоматичне завантаження з файлу
        phoneList = FileManager.loadFromJson(FILE_NAME);

        boolean running = true;
        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ (ПРАКТИЧНА №9 - JSON) ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми");
            System.out.print("Оберіть пункт (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu();
                    break;
                case "2":
                    printAllObjects();
                    break;
                case "3":
                    running = false;
                    // Автоматичне збереження у файл при виході
                    FileManager.saveToJson(phoneList, FILE_NAME);
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть 1, 2 або 3.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("=================================================");
        System.out.println("    ПРАКТИЧНА РОБОТА №9 - ЗБЕРЕЖЕННЯ В JSON      ");
        System.out.println("    Автозавантаження та автозбереження даних     ");
        System.out.println("=================================================");
    }

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
                    System.out.println("Некоректний вибір типа об'єкта.");
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