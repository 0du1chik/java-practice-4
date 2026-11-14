package com.example;

import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store("TestStore");
        store.addNewPhone(new SmartPhone("Samsung", "Galaxy S24", 899.0, 12, PhoneType.SMARTPHONE, "Android"), 2);
        store.addNewPhone(new SmartPhone("Apple", "iPhone 15 Pro", 999.0, 8, PhoneType.SMARTPHONE, "iOS"), 5);
        store.addNewPhone(new KeypadPhone("Nokia", "3310", 50.0, 1, PhoneType.FEATURE_PHONE, true), 10);
    }

    @Test
    void shouldSortByPriceAscending() {
        ArrayList<StoreItem> sorted = store.getItemsSortedByPrice();
        assertEquals(50.0, sorted.get(0).getPhone().getPrice());
        assertEquals(899.0, sorted.get(1).getPhone().getPrice());
        assertEquals(999.0, sorted.get(2).getPhone().getPrice());
    }

    @Test
    void shouldSortByRamDescending() {
        ArrayList<StoreItem> sorted = store.getItemsSortedByRamDesc();
        assertEquals(12, sorted.get(0).getPhone().getRamGb());
        assertEquals(8, sorted.get(1).getPhone().getRamGb());
        assertEquals(1, sorted.get(2).getPhone().getRamGb());
    }

    @Test
    void shouldSortByBrandAlphabetically() {
        ArrayList<StoreItem> sorted = store.getItemsSortedByBrand();
        assertEquals("Apple", sorted.get(0).getPhone().getBrand());
        assertEquals("Nokia", sorted.get(1).getPhone().getBrand());
        assertEquals("Samsung", sorted.get(2).getPhone().getBrand());
    }

    @Test
    void shouldHandleEmptyStoreAndSingleItem() {
        Store emptyStore = new Store("Empty");
        assertTrue(emptyStore.getItemsSortedByPrice().isEmpty());

        emptyStore.addNewPhone(new SmartPhone("Sony", "Xperia", 700.0, 8, PhoneType.SMARTPHONE, "Android"), 1);
        ArrayList<StoreItem> singleSorted = emptyStore.getItemsSortedByBrand();
        assertEquals(1, singleSorted.size());
        assertEquals("Sony", singleSorted.get(0).getPhone().getBrand());
    }

    @Test
    void shouldHandleEqualValuesGracefully() {
        Store equalStore = new Store("EqualStore");
        equalStore.addNewPhone(new SmartPhone("Xiaomi", "14", 500.0, 8, PhoneType.SMARTPHONE, "Android"), 1);
        equalStore.addNewPhone(new SmartPhone("Realme", "GT", 500.0, 8, PhoneType.SMARTPHONE, "Android"), 1);

        ArrayList<StoreItem> sortedByPrice = equalStore.getItemsSortedByPrice();
        assertEquals(2, sortedByPrice.size());
        assertEquals(500.0, sortedByPrice.get(0).getPhone().getPrice());
        assertEquals(500.0, sortedByPrice.get(1).getPhone().getPrice());
    }
}