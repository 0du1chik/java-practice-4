package com.example;

import com.example.model.KeypadPhone;
import com.example.model.Phone;
import com.example.model.PhoneType;
import com.example.model.SmartPhone;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Драйвер-клас для демонстрації успадкування та поліморфізму.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Phone> phoneList = new ArrayList<>();

    public static void main(String[] args) {
        printHeader();
        boolean running = true;

        while (running) {
            System.out.println("\n=== МЕНЮ УПРАВЛІННЯ (ПРАКТИЧНА №7 - ПОЛІМОРФІЗМ) ===");
            System.out.println("1. Додати базовий телефон (Phone)");
            System.out.println("2. Додати смартфон (SmartPhone)");
            System.out.println("3. Додати кнопковий телефон (KeypadPhone)");
            System.out.println("4. Вивести всі телефони з однієї колекції (Поліморфізм)");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт (1-5): ");

            String choice = scanner.nextLine().trim();

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
                    printAllPhones();
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
        System.out.println("    ПРАКТИЧНА РОБОТА №7 - НАСЛІДУВАННЯ ТА ПОЛІМОРФІЗМ");
        System.out.println("    Предметна область: Phone, SmartPhone, KeypadPhone");
        System.out.println("=================================================");
    }

    private static void addBasePhone() {
        System.out.println("\n    Додавання базового телефону (Phone)    ");
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();
            System.out.print("Введіть ціну ($): ");
            double price = readDouble();
            System.out.print("Введіть обсяг ОЗП (ГБ): ");
            int ramGb = readInt();

            Phone phone = new Phone(brand, model, price, ramGb, PhoneType.SMARTPHONE);
            phoneList.add(phone);
            System.out.println("Базовий телефон успішно додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void addSmartPhone() {
        System.out.println("\n    Додавання смартфона (SmartPhone)    ");
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();
            System.out.print("Введіть ціну ($): ");
            double price = readDouble();
            System.out.print("Введіть обсяг ОЗП (ГБ): ");
            int ramGb = readInt();
            System.out.print("Введіть ОС (iOS/Android): ");
            String os = scanner.nextLine();

            SmartPhone smartPhone = new SmartPhone(brand, model, price, ramGb, PhoneType.SMARTPHONE, os);
            phoneList.add(smartPhone);
            System.out.println("Смартфон успішно додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void addKeypadPhone() {
        System.out.println("\n    Додавання кнопкового телефону (KeypadPhone)    ");
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();
            System.out.print("Введіть ціну ($): ");
            double price = readDouble();
            System.out.print("Введіть обсяг ОЗП (ГБ): ");
            int ramGb = readInt();
            System.out.print("Чи є ліхтарик? (1 - Так, 0 - Ні): ");
            boolean hasTorch = readInt() == 1;

            KeypadPhone keypadPhone = new KeypadPhone(brand, model, price, ramGb, PhoneType.FEATURE_PHONE, hasTorch);
            phoneList.add(keypadPhone);
            System.out.println("Кнопковий телефон успішно додано!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void printAllPhones() {
        System.out.println("\n    Вивід усіх об'єктів з одного ArrayList<Phone> (Поліморфізм)    ");
        if (phoneList.isEmpty()) {
            System.out.println("Колекція порожня.");
        } else {
            for (int i = 0; i < phoneList.size(); i++) {
                // Демонстрація поліморфізму: викликається відповідний toString() залежно від реального типу
                System.out.println((i + 1) + ". " + phoneList.get(i));
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