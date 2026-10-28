package com.example;

import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    private ArrayList<Phone> testList;

    @BeforeEach
    void setUp() {
        testList = new ArrayList<>();
        testList.add(new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS"));
        testList.add(new KeypadPhone("Nokia", "3310", 40.0, 1, PhoneType.FEATURE_PHONE, true));
        testList.add(new SecureKeypadPhone("CAT", "B35", 150.0, 2, PhoneType.FEATURE_PHONE, true, "IP68"));
    }

    @Test
    void shouldFindByBrandIgnoringCase() {
        ArrayList<Phone> result = Main.findByBrand(testList, "apple");
        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getBrand());
    }

    @Test
    void shouldFindByMaxPrice() {
        ArrayList<Phone> result = Main.findByMaxPrice(testList, 200.0);
        assertEquals(2, result.size()); // Nokia (40) та CAT (150)
    }

    @Test
    void shouldFindByMinRam() {
        ArrayList<Phone> result = Main.findByMinRam(testList, 2);
        assertEquals(2, result.size()); // Apple (8) та CAT (2)
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchFound() {
        ArrayList<Phone> result = Main.findByBrand(testList, "Samsung");
        assertTrue(result.isEmpty());
    }
}