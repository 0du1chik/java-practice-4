package com.example.model;

/**
 * Перерахування типів телефонів.
 */
public enum PhoneType {
    SMARTPHONE("Смартфон"),
    FEATURE_PHONE("Кнопковий телефон"),
    GAMING_PHONE("Ігровий смартфон");

    private final String displayName;

    PhoneType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}