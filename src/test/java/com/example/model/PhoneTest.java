package com.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @BeforeEach
    void setUp() {
        Phone.resetObjectCount();
    }

    @Test
    void shouldCreatePhoneAndIncrementStaticCounter() {
        assertEquals(0, Phone.getObjectCount());

        Phone p1 = new Phone("Apple", "iPhone 15 Pro", 999.0, 8, PhoneType.SMARTPHONE);
        assertEquals(1, Phone.getObjectCount());

        Phone p2 = new Phone("Nokia", "3310", 50.0, 1, PhoneType.FEATURE_PHONE);
        assertEquals(2, Phone.getObjectCount());
    }

    @Test
    void shouldCopyPhoneCorrectly() {
        Phone original = new Phone("Samsung", "Galaxy S24", 799.0, 8, PhoneType.SMARTPHONE);
        Phone copy = new Phone(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
        assertEquals(2, Phone.getObjectCount());
    }

    @Test
    void shouldThrowExceptionWhenCopyingNull() {
        assertThrows(IllegalArgumentException.class, () -> new Phone((Phone) null));
    }

    @Test
    void shouldAggregatePhonesInStore() {
        Store store = new Store("TechStore");
        Phone phone = new Phone("Asus", "ROG Phone 7", 1100.0, 16, PhoneType.GAMING_PHONE);

        store.addPhone(phone);

        assertEquals(1, store.getPhones().size());
        assertEquals(phone, store.getPhones().get(0));
    }
}