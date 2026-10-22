package com.example.model;

import java.util.Objects;

/**
 * Похідний клас KeypadPhone, що успадковує Phone.
 */
public class KeypadPhone extends Phone {
    private boolean hasTorch;

    public KeypadPhone(String brand, String model, double price, int ramGb, PhoneType type, boolean hasTorch) {
        super(brand, model, price, ramGb, type);
        this.hasTorch = hasTorch;
    }

    public boolean isHasTorch() {
        return hasTorch;
    }

    public void setHasTorch(boolean hasTorch) {
        this.hasTorch = hasTorch;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        KeypadPhone that = (KeypadPhone) o;
        return hasTorch == that.hasTorch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasTorch);
    }

    @Override
    public String toString() {
        return "KeypadPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", ramGb=" + getRamGb() +
                ", type=" + getType().getDisplayName() +
                ", hasTorch=" + (hasTorch ? "Так" : "Ні") +
                '}';
    }
}