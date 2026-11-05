package com.example.model;

import java.util.Objects;

/**
 * Абстрактний базовий клас для ієрархії телефонів.
 * Реалізує інтерфейс Comparable для сортування за брендом та моделлю.
 */
public abstract class Phone implements Comparable<Phone> {
    private String brand;
    private String model;
    private double price;
    private int ramGb;
    private PhoneType type;

    public Phone(String brand, String model, double price, int ramGb, PhoneType type) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім.");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною.");
        }
        if (ramGb <= 0) {
            throw new IllegalArgumentException("Обсяг ОЗП повинен бути більшим за 0.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Тип телефону не може бути null.");
        }
        this.brand = brand.trim();
        this.model = model.trim();
        this.price = price;
        this.ramGb = ramGb;
        this.type = type;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public int getRamGb() { return ramGb; }
    public PhoneType getType() { return type; }

    @Override
    public int compareTo(Phone o) {
        if (o == null) return 1;
        int brandCompare = this.brand.compareToIgnoreCase(o.brand);
        if (brandCompare != 0) {
            return brandCompare;
        }
        return this.model.compareToIgnoreCase(o.model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
                ramGb == phone.ramGb &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(model, phone.model) &&
                type == phone.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, ramGb, type);
    }

    @Override
    public String toString() {
        return String.format("%s{brand='%s', model='%s', price=%.1f, ramGb=%d, type=%s}",
                getClass().getSimpleName(), brand, model, price, ramGb, type.getDisplayName());
    }
}