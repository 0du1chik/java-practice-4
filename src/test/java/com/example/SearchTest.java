package com.example;

import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store("TestStore");
        store.addNewPhone(new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS"), 1);
        store.addNewPhone(new KeypadPhone("Nokia", "3310", 40.0, 1, PhoneType.FEATURE_PHONE, true), 1);
        store.addNewPhone(new SecureKeypadPhone("CAT", "B35", 150.0, 2, PhoneType.FEATURE_PHONE, true, "IP68"), 1);
    }

    @Test
    void shouldFindByBrandIgnoringCase() {
        ArrayList<StoreItem> result = store.findByBrand("apple");
        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getPhone().getBrand());
    }

    @Test
    void shouldFindByMaxPrice() {
        ArrayList<StoreItem> result = store.findByMaxPrice(200.0);
        assertEquals(2, result.size()); // Nokia (40) та CAT (150)
    }

    @Test
    void shouldFindByMinRam() {
        ArrayList<StoreItem> result = store.findByMinRam(2);
        assertEquals(2, result.size()); // Apple (8) та CAT (2)
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchFound() {
        ArrayList<StoreItem> result = store.findByBrand("Samsung");
        assertTrue(result.isEmpty());
    }
}