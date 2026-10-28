package com.example;

import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store("TestStore");
        store.addNewPhone(new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS"), 3);
        store.addNewPhone(new KeypadPhone("Nokia", "3310", 40.0, 1, PhoneType.FEATURE_PHONE, true), 10);
    }

    @Test
    void shouldAddNewPhoneToStore() {
        assertEquals(2, store.getItems().size());
        assertEquals(3, store.getItems().get(0).getQuantity());
    }

    @Test
    void shouldIncreaseQuantityWhenSamePhoneAdded() {
        SmartPhone duplicatePhone = new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");
        store.addNewPhone(duplicatePhone, 5);

        assertEquals(2, store.getItems().size());
        assertEquals(8, store.getItems().get(0).getQuantity());
    }

    @Test
    void shouldSearchByBrandInStore() {
        assertEquals(1, store.findByBrand("Apple").size());
        assertEquals(0, store.findByBrand("Samsung").size());
    }
}