package com.example.model;

import java.util.Objects;

/**
 * Клас захищеного кнопкового телефону (нащадок KeypadPhone).
 */
public class SecureKeypadPhone extends KeypadPhone {
    private String protectionStandard; // наприклад, IP68, MIL-STD-810G

    public SecureKeypadPhone(String brand, String model, double price, int ramGb, PhoneType type, boolean hasTorch, String protectionStandard) {
        super(brand, model, price, ramGb, type, hasTorch);
        setProtectionStandard(protectionStandard);
    }

    public String getProtectionStandard() {
        return protectionStandard;
    }

    public void setProtectionStandard(String protectionStandard) {
        if (protectionStandard == null || protectionStandard.trim().isEmpty()) {
            throw new IllegalArgumentException("Стандарт захисту не може бути порожнім.");
        }
        this.protectionStandard = protectionStandard.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        SecureKeypadPhone that = (SecureKeypadPhone) o;
        return Objects.equals(protectionStandard, that.protectionStandard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), protectionStandard);
    }

    @Override
    public String toString() {
        return "SecureKeypadPhone{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", price=" + getPrice() +
                ", ramGb=" + getRamGb() +
                ", hasTorch=" + (isHasTorch() ? "Так" : "Ні") +
                ", protectionStandard='" + protectionStandard + '\'' +
                '}';
    }
}