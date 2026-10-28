package com.example.util;

import com.example.model.*;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void shouldSaveAndLoadStoreJsonCorrectly() {
        String testFile = "test_store_output.json";
        Store originalStore = new Store("TestStore");
        originalStore.addNewPhone(new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS"), 4);
        originalStore.addNewPhone(new SecureKeypadPhone("CAT", "B35", 150.0, 1, PhoneType.FEATURE_PHONE, true, "IP68"), 2);

        FileManager.saveToJson(originalStore, testFile);

        Store loadedStore = FileManager.loadFromJson(testFile, "LoadedStore");

        assertEquals(2, loadedStore.getItems().size());
        assertEquals(4, loadedStore.getItems().get(0).getQuantity());
        assertEquals(2, loadedStore.getItems().get(1).getQuantity());

        new File(testFile).delete();
    }
}