package com.example.model;

import java.util.Objects;
import java.util.UUID;

public abstract class Phone implements Comparable<Phone>, Identifiable {
    private UUID uuid;
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
        this.uuid = UUID.randomUUID();
        this.brand = brand.trim();
        this.model = model.trim();
        this.price = price;
        this.ramGb = ramGb;
        this.type = type;
    }

    @Override
    public UUID getUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public int getRamGb() { return ramGb; }
    public PhoneType getType() { return type; }

    public String toShortString() {
        return String.format("%s: %s %s | UUID: %s",
                getClass().getSimpleName(), brand, model, getUuid());
    }

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
        return String.format("%s{uuid=%s, brand='%s', model='%s', price=%.1f, ramGb=%d, type=%s}",
                getClass().getSimpleName(), getUuid(), brand, model, price, ramGb, type.getDisplayName());
    }
}