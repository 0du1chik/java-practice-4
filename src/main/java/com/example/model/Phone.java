package com.example.model;

import java.util.Objects;

/**
 * Клас, що описує телефон.
 */
public class Phone {
    private static int objectCount = 0;

    private String brand;
    private String model;
    private double price;
    private int ramGb;
    private PhoneType type;

    /**
     * Конструктор з параметрами.
     *
     * @param brand назва бренду
     * @param model назва моделі
     * @param price ціна ($)
     * @param ramGb обсяг ОЗП (ГБ)
     * @param type тип телефону
     */
    public Phone(String brand, String model, double price, int ramGb, PhoneType type) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setRamGb(ramGb);
        setType(type);
        objectCount++;
    }

    /**
     * Конструктор копіювання.
     *
     * @param other об'єкт Phone для копіювання
     */
    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.ramGb = other.ramGb;
        this.type = other.type;
        objectCount++;
    }

    public static int getObjectCount() {
        return objectCount;
    }

    public static void resetObjectCount() {
        objectCount = 0;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім.");
        }
        this.brand = brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не може бути порожньою.");
        }
        this.model = model.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0.");
        }
        this.price = price;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        if (ramGb <= 0) {
            throw new IllegalArgumentException("Обсяг ОЗП повинен бути більшим за 0.");
        }
        this.ramGb = ramGb;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип телефону не може бути null.");
        }
        this.type = type;
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
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", ramGb=" + ramGb +
                ", type=" + type.getDisplayName() +
                '}';
    }
}