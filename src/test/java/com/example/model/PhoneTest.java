package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void shouldCreateSmartPhoneCorrectly() {
        SmartPhone smartPhone = new SmartPhone("Apple", "iPhone 15 Pro", 999.0, 8, PhoneType.SMARTPHONE, "iOS");
        assertEquals("iOS", smartPhone.getOperatingSystem());
        assertEquals("Apple", smartPhone.getBrand());
    }

    @Test
    void shouldCreateKeypadPhoneCorrectly() {
        KeypadPhone keypadPhone = new KeypadPhone("Nokia", "3310", 40.0, 1, PhoneType.FEATURE_PHONE, true);
        assertTrue(keypadPhone.isHasTorch());
        assertEquals("Nokia", keypadPhone.getBrand());
    }

    @Test
    void shouldThrowExceptionWhenInvalidSmartPhoneData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SmartPhone("Samsung", "S24", 800.0, 8, PhoneType.SMARTPHONE, "");
        });
    }

    @Test
    void shouldDemonstratePolymorphism() {
        Phone base = new Phone("Generic", "Phone", 100.0, 2, PhoneType.FEATURE_PHONE);
        Phone smart = new SmartPhone("Google", "Pixel 8", 699.0, 8, PhoneType.SMARTPHONE, "Android");

        assertTrue(smart instanceof Phone);
        assertNotEquals(base.toString(), smart.toString());
    }
}