package com.example.model;

import java.util.Objects;

/**
 * Клас, що описує телефон.
 */
public class Phone {
    private String brand;
    private String model;
    private double price;
    private int ramGb;

    /**
     * Конструктор класу Phone з перевіркою параметрів.
     *
     * @param brand назва бренду (не порожня)
     * @param model назва моделі (не порожня)
     * @param price ціна ($) (більше 0)
     * @param ramGb обсяг OЗП у ГБ (більше 0)
     */
    public Phone(String brand, String model, double price, int ramGb) {
        setBrand(brand);
        setModel(model);
        setPrice(price);
        setRamGb(ramGb);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.price, price) == 0 &&
                ramGb == phone.ramGb &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(model, phone.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, ramGb);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", ramGb=" + ramGb +
                '}';
    }
}