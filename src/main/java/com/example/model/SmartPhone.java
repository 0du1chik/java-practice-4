package com.example.model;

import java.util.Objects;

/**
 * Похідний клас SmartPhone, що успадковує Phone.
 */
public class SmartPhone extends Phone {
    private String operatingSystem;

    public SmartPhone(String brand, String model, double price, int ramGb, PhoneType type, String operatingSystem) {
        super(brand, model, price, ramGb, type);
        setOperatingSystem(operatingSystem);
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if (operatingSystem == null || operatingSystem.trim().isEmpty()) {
            throw new IllegalArgumentException("Операційна система не може бути порожньою.");
        }
        this.operatingSystem = operatingSystem.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        SmartPhone that = (SmartPhone) o;
        return Objects.equals(operatingSystem, that.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operatingSystem);
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", ramGb=" + getRamGb() +
                ", type=" + getType().getDisplayName() +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}