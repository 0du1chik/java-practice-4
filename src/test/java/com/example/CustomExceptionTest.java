package com.example;

import com.example.exception.InvalidFieldValueException;
import com.example.exception.ObjectNotFoundException;
import com.example.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomExceptionTest {

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingObject() {
        Store store = new Store("TestStore");
        SmartPhone phone = new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");

        assertThrows(ObjectNotFoundException.class, () -> {
            store.delete(phone);
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenUpdatingNonExistingObject() {
        Store store = new Store("TestStore");
        SmartPhone existing = new SmartPhone("Samsung", "S24", 899.0, 8, PhoneType.SMARTPHONE, "Android");
        SmartPhone replacement = new SmartPhone("Samsung", "S24 Ultra", 1199.0, 12, PhoneType.SMARTPHONE, "Android");

        assertThrows(ObjectNotFoundException.class, () -> {
            store.update(existing, replacement);
        });
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionForInvalidPhoneData() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new SmartPhone("", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");
        });

        assertThrows(InvalidFieldValueException.class, () -> {
            new SmartPhone("Apple", "iPhone 15", -50.0, 8, PhoneType.SMARTPHONE, "iOS");
        });

        assertThrows(InvalidFieldValueException.class, () -> {
            new KeypadPhone("Nokia", "3310", 50.0, 0, PhoneType.FEATURE_PHONE, true);
        });
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenPassingNullToStore() {
        Store store = new Store("TestStore");
        SmartPhone phone = new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");

        assertThrows(InvalidFieldValueException.class, () -> {
            store.addNewPhone(null, 5);
        });

        assertThrows(InvalidFieldValueException.class, () -> {
            store.delete(null);
        });

        assertThrows(InvalidFieldValueException.class, () -> {
            store.update(phone, null);
        });
    }
}