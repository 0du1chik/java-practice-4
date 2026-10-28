package com.example.model;

import java.util.Objects;

/**
 * Клас-обгортка для зберігання товару (Phone) та його кількості в магазині.
 */
public class StoreItem {
    private Phone phone;
    private int quantity;

    public StoreItem(Phone phone, int quantity) {
        if (phone == null) {
            throw new IllegalArgumentException("Телефон не може бути null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість товару повинна бути більшою за 0.");
        }
        this.phone = phone;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість товару повинна бути більшою за 0.");
        }
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Кількість для додавання повинна бути більшою за 0.");
        }
        this.quantity += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreItem storeItem = (StoreItem) o;
        return Objects.equals(phone, storeItem.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    @Override
    public String toString() {
        return phone.toString() + " | Кількість на складі: " + quantity + " шт.";
    }
}