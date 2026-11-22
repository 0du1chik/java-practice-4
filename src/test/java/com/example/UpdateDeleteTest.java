package com.example;

import com.example.exception.InvalidFieldValueException;
import com.example.exception.ObjectNotFoundException;
import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDeleteTest {

    private Store store;
    private SmartPhone iphone;
    private KeypadPhone nokia;

    @BeforeEach
    void setUp() {
        store = new Store("TestStore");
        iphone = new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");
        nokia = new KeypadPhone("Nokia", "3310", 59.0, 1, PhoneType.FEATURE_PHONE, true);
        store.addNewPhone(iphone, 5);
        store.addNewPhone(nokia, 10);
    }

    @Test
    void shouldUpdateExistingPhoneAndPreserveUuid() {
        SmartPhone updatedIphone = new SmartPhone("Apple", "iPhone 15 Pro Max", 1199.0, 8, PhoneType.SMARTPHONE, "iOS");
        boolean result = store.update(iphone, updatedIphone);

        assertTrue(result);
        assertEquals(2, store.getItems().size());
        assertEquals("iPhone 15 Pro Max", store.getItems().get(0).getPhone().getModel());
        assertEquals(1199.0, store.getItems().get(0).getPhone().getPrice());
        assertEquals(iphone.getUuid(), store.getItems().get(0).getPhone().getUuid());
    }

    @Test
    void shouldThrowExceptionsWhenUpdatingNonExistentOrNullPhone() {
        SmartPhone unknown = new SmartPhone("Sony", "Xperia", 700.0, 8, PhoneType.SMARTPHONE, "Android");
        SmartPhone replacement = new SmartPhone("Sony", "Xperia 1 V", 900.0, 12, PhoneType.SMARTPHONE, "Android");

        assertThrows(ObjectNotFoundException.class, () -> store.update(unknown, replacement));
        assertThrows(InvalidFieldValueException.class, () -> store.update(null, replacement));
        assertThrows(InvalidFieldValueException.class, () -> store.update(iphone, null));
    }

    @Test
    void shouldDeleteExistingPhone() {
        boolean deleted = store.delete(nokia);

        assertTrue(deleted);
        assertEquals(1, store.getItems().size());
        assertEquals("Apple", store.getItems().get(0).getPhone().getBrand());
    }

    @Test
    void shouldThrowExceptionsWhenDeletingNonExistentOrFromEmptyStore() {
        SmartPhone unknown = new SmartPhone("LG", "Velvet", 400.0, 6, PhoneType.SMARTPHONE, "Android");
        assertThrows(ObjectNotFoundException.class, () -> store.delete(unknown));
        assertThrows(InvalidFieldValueException.class, () -> store.delete(null));

        Store emptyStore = new Store("Empty");
        assertThrows(ObjectNotFoundException.class, () -> emptyStore.delete(iphone));
    }
}