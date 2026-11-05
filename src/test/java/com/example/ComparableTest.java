package com.example;

import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComparableTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store("TestStore");
        store.addNewPhone(new SmartPhone("Samsung", "Galaxy S24", 899.0, 12, PhoneType.SMARTPHONE, "Android"), 2);
        store.addNewPhone(new SmartPhone("Apple", "iPhone 15 Pro", 999.0, 8, PhoneType.SMARTPHONE, "iOS"), 5);
        store.addNewPhone(new KeypadPhone("Apple", "iPhone 13", 699.0, 4, PhoneType.SMARTPHONE, false), 1);
    }

    @Test
    void shouldSortItemsByBrandAndModel() {
        ArrayList<StoreItem> sorted = store.getSortedItems();
        assertEquals(3, sorted.size());
        
        // Очікуваний порядок: 
        // 1. Apple iPhone 13
        // 2. Apple iPhone 15 Pro
        // 3. Samsung Galaxy S24
        assertEquals("iPhone 13", sorted.get(0).getPhone().getModel());
        assertEquals("iPhone 15 Pro", sorted.get(1).getPhone().getModel());
        assertEquals("Samsung", sorted.get(2).getPhone().getBrand());
    }

    @Test
    void shouldHandleEmptyStoreSorting() {
        Store emptyStore = new Store("Empty");
        assertTrue(emptyStore.getSortedItems().isEmpty());
    }
}