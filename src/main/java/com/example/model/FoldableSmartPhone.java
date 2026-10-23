package com.example.model;

import java.util.Objects;

/**
 * Клас складного смартфона (нащадок SmartPhone).
 */
public class FoldableSmartPhone extends SmartPhone {
    private boolean isDualScreen;

    public FoldableSmartPhone(String brand, String model, double price, int ramGb, PhoneType type, String operatingSystem, boolean isDualScreen) {
        super(brand, model, price, ramGb, type, operatingSystem);
        this.isDualScreen = isDualScreen;
    }

    public boolean isDualScreen() {
        return isDualScreen;
    }

    public void setDualScreen(boolean dualScreen) {
        isDualScreen = dualScreen;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        FoldableSmartPhone that = (FoldableSmartPhone) o;
        return isDualScreen == that.isDualScreen;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isDualScreen);
    }

    @Override
    public String toString() {
        return "FoldableSmartPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", ramGb=" + getRamGb() +
                ", OS='" + getOperatingSystem() + '\'' +
                ", isDualScreen=" + (isDualScreen ? "Так" : "Ні") +
                '}';
    }
}