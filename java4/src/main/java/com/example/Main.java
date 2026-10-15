package com.example;

import com.example.model.Phone;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phones = new ArrayList<>();

        System.out.println(" Введення даних для 5 телефонів ");

        for (int i = 0; i < 5; i++) {
            System.out.println("\nТелефон #" + (i + 1) + ":");
            
            System.out.print("Бренд: ");
            String brand = scanner.nextLine();

            System.out.print("Модель: ");
            String model = scanner.nextLine();

            System.out.print("Ціна ($): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Очищення буфера

            phones.add(new Phone(brand, model, price));
        }

        System.out.println("\n Список усіх телефонів (ArrayList) ");
        for (Phone phone : phones) {
            System.out.println(phone);
        }

        scanner.close();
    }
}