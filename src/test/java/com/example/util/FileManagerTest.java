package com.example.util;

import com.example.model.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void shouldSaveAndLoadJsonCorrectly() {
        String testFile = "test_output.json";
        ArrayList<Phone> originalList = new ArrayList<>();
        originalList.add(new SmartPhone("Apple", "iPhone 15", 999.0, 8, PhoneType.SMARTPHONE, "iOS"));
        originalList.add(new SecureKeypadPhone("CAT", "B35", 150.0, 1, PhoneType.FEATURE_PHONE, true, "IP68"));

        FileManager.saveToJson(originalList, testFile);

        ArrayList<Phone> loadedList = FileManager.loadFromJson(testFile);

        assertEquals(2, loadedList.size());
        assertTrue(loadedList.get(0) instanceof SmartPhone);
        assertTrue(loadedList.get(1) instanceof SecureKeypadPhone);

        new File(testFile).delete();
    }
}