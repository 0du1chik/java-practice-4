package com.example;

import com.example.model.Phone;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Головний клас-драйвер із консольним меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Phone> phones = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== МЕНЮ УПРАВЛІННЯ ===");
            System.out.println("1. Додати новий телефон");
            System.out.println("2. Вивести інформацію про всі телефони");
            System.out.println("3. Завершити роботу");
            System.out.print("Оберіть пункт (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addPhone();
                    break;
                case "2":
                    printAllPhones();
                    break;
                case "3":
                    running = false;
                    System.out.println("Програму завершено. До побачення!");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 1 до 3.");
            }
        }
    }

    private static void addPhone() {
        System.out.println("\n--- Додавання нового телефону ---");
        try {
            System.out.print("Введіть бренд: ");
            String brand = scanner.nextLine();

            System.out.print("Введіть модель: ");
            String model = scanner.nextLine();

            System.out.print("Введіть ціну ($): ");
            double price = readDouble();

            System.out.print("Введіть обсяг ОЗП (ГБ): ");
            int ramGb = readInt();

            Phone phone = new Phone(brand, model, price, ramGb);
            phones.add(phone);
            System.out.println("Телефон успішно додано!");

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void printAllPhones() {
        System.out.println("\n--- Список усіх телефонів ---");
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < phones.size(); i++) {
                System.out.println((i + 1) + ". " + phones.get(i));
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Некоректний ввід! Введіть дійсне число: ");
            }
        }
    }

    private static int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Некоректний ввід! Введіть ціле число: ");
            }
        }
    }
}