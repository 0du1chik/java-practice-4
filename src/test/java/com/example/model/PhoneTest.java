package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @Test
    void shouldCreateFoldableSmartPhoneCorrectly() {
        FoldableSmartPhone phone = new FoldableSmartPhone("Samsung", "Galaxy Fold 5", 1800.0, 12, PhoneType.SMARTPHONE, "Android", true);
        assertTrue(phone.isDualScreen());
        assertEquals("Android", phone.getOperatingSystem());
        assertEquals("Samsung", phone.getBrand());
    }

    @Test
    void shouldCreateSecureKeypadPhoneCorrectly() {
        SecureKeypadPhone phone = new SecureKeypadPhone("CAT", "B35", 150.0, 1, PhoneType.FEATURE_PHONE, true, "IP68");
        assertEquals("IP68", phone.getProtectionStandard());
        assertTrue(phone.isHasTorch());
        assertEquals("CAT", phone.getBrand());
    }

    @Test
    void shouldThrowExceptionWhenInvalidProtectionStandard() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SecureKeypadPhone("CAT", "B35", 150.0, 1, PhoneType.FEATURE_PHONE, true, "");
        });
    }
}