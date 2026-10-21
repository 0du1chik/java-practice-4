package com.example;

import com.example.model.Phone;
import com.example.model.PhoneType;
import com.example.model.Store;

import java.util.Scanner;

/**
 * Драйвер-клас із графічною інформаційною шапкою та меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store("Мобільний Світ");

    public static void main(String[] args) {
        printHeader();
        boolean running = true;

        while (running) {
            System.out.println("\n=== МЕНЮ УПРАВЛІННЯ (ПРАКТИЧНА №6) ===");
            System.out.println("1. Додати новий телефон до магазину");
            System.out.println("2. Створити копію останнього телефону (конструктор копіювання)");
            System.out.println("3. Вивести інформацію про магазин та всі телефони");
            System.out.println("4. Показати кількість створених об'єктів (static counter)");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addPhone();
                    break;
                case "2":
                    copyLastPhone();
                    break;
                case "3":
                    printStoreInfo();
                    break;
                case "4":
                    System.out.println("Загалом створено об'єктів Phone (static count): " + Phone.getObjectCount());
                    break;
                case "5":
                    running = false;
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 1 до 5.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("=================================================");
        System.out.println("    ПРАКТИЧНА РОБОТА №6 - СТАТИЧНІ ЧЛЕНИ ТА ENUM   ");
        System.out.println("    Предметна область: Phone & Store             ");
        System.out.println("=================================================");
    }

    private static void addPhone() {
        System.out.println("\n    Додавання нового телефону    ");
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();

            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();

            System.out.print("Введіть ціну ($): ");
            double price = readDouble();

            System.out.print("Введіть обсяг ОЗП (ГБ): ");
            int ramGb = readInt();

            System.out.println("Оберіть тип телефону:");
            System.out.println("1 - Смартфон");
            System.out.println("2 - Кнопковий телефон");
            System.out.println("3 - Ігровий смартфон");
            System.out.print("Вибір (1-3): ");
            PhoneType type = readPhoneType();

            Phone phone = new Phone(brand, model, price, ramGb, type);
            store.addPhone(phone);
            System.out.println("Телефон успішно додано до магазину!");

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void copyLastPhone() {
        if (store.getPhones().isEmpty()) {
            System.out.println("Помилка: Немає телефонів для копіювання!");
            return;
        }
        Phone last = store.getPhones().get(store.getPhones().size() - 1);
        Phone copy = new Phone(last);
        store.addPhone(copy);
        System.out.println("Створено копію телефону через конструктор копіювання:");
        System.out.println(copy);
    }

    private static void printStoreInfo() {
        System.out.println("\n    Інформація про магазин    ");
        System.out.println(store);
        System.out.println("    Список телефонів    ");
        if (store.getPhones().isEmpty()) {
            System.out.println("Магазин порожній.");
        } else {
            for (int i = 0; i < store.getPhones().size(); i++) {
                System.out.println((i + 1) + ". " + store.getPhones().get(i));
            }
        }
    }

    private static PhoneType readPhoneType() {
        while (true) {
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    return PhoneType.SMARTPHONE;
                case "2":
                    return PhoneType.FEATURE_PHONE;
                case "3":
                    return PhoneType.GAMING_PHONE;
                default:
                    System.out.print("Некоректний вибір! Введіть 1, 2 або 3: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Некоректний ввід! Введіть дійсне число: ");
            }
        }
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Некоректний ввід! Введіть ціле число: ");
            }
        }
    }
}