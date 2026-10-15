package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void shouldCreatePhoneWhenValidData() {
        Phone phone = new Phone("Apple", "iPhone 15 Pro", 999.0, 8);
        assertEquals("Apple", phone.getBrand());
        assertEquals("iPhone 15 Pro", phone.getModel());
        assertEquals(999.0, phone.getPrice());
        assertEquals(8, phone.getRamGb());
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("", "Galaxy S24", 799.0, 8);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Samsung", "Galaxy S24", -100.0, 8);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Phone("Samsung", "Galaxy S24", 799.0, 0);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Phone phone = new Phone("Google", "Pixel 8", 699.0, 8);

        assertThrows(IllegalArgumentException.class, () -> {
            phone.setPrice(-50.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            phone.setBrand("   ");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            phone.setRamGb(-4);
        });
    }
}