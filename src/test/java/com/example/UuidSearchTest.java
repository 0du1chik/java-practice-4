package com.example;

import com.example.model.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class UuidSearchTest {

    @Test
    void shouldGenerateUniqueUuidAndFindItem() {
        Store store = new Store("TestStore");
        SmartPhone phone1 = new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS");
        SmartPhone phone2 = new SmartPhone("Samsung", "S24", 899.0, 12, PhoneType.SMARTPHONE, "Android");

        assertNotNull(phone1.getUuid());
        assertNotEquals(phone1.getUuid(), phone2.getUuid());

        store.addNewPhone(phone1, 2);
        store.addNewPhone(phone2, 3);

        StoreItem found = store.findByUuid(phone1.getUuid());
        assertNotNull(found);
        assertEquals("iPhone 15", found.getPhone().getModel());
        assertNull(store.findByUuid(UUID.randomUUID()));
    }
}