package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що демонструє агрегацію (магазин зберігає список телефонів).
 */
public class Store {
    private String name;
    private final List<Phone> phones;

    /**
     * Конструктор магазину.
     *
     * @param name назва магазину
     */
    public Store(String name) {
        setName(name);
        this.phones = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва магазину не може бути порожньою.");
        }
        this.name = name.trim();
    }

    /**
     * Додає телефон у магазин (агрегація).
     */
    public void addPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null.");
        }
        phones.add(phone);
    }

    public List<Phone> getPhones() {
        return new ArrayList<>(phones);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", countOfPhones=" + phones.size() +
                '}';
    }
}