package com.example.util;

import com.example.model.*;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Сервісний клас для читання та запису даних магазину Store у JSON-файл за допомогою Gson.
 */
public class FileManager {

    public static Store loadFromJson(String fileName, String storeName) {
        Store store = new Store(storeName);
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(fileName)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (!jsonObject.has("classType")) {
                    continue;
                }

                String classType = jsonObject.get("classType").getAsString();
                int quantity = jsonObject.has("quantity") ? jsonObject.get("quantity").getAsInt() : 1;

                Phone phone = null;
                if ("Phone".equals(classType)) {
                    phone = gson.fromJson(jsonObject, Phone.class);
                } else if ("SmartPhone".equals(classType)) {
                    phone = gson.fromJson(jsonObject, SmartPhone.class);
                } else if ("KeypadPhone".equals(classType)) {
                    phone = gson.fromJson(jsonObject, KeypadPhone.class);
                } else if ("FoldableSmartPhone".equals(classType)) {
                    phone = gson.fromJson(jsonObject, FoldableSmartPhone.class);
                } else if ("SecureKeypadPhone".equals(classType)) {
                    phone = gson.fromJson(jsonObject, SecureKeypadPhone.class);
                }

                if (phone != null) {
                    store.addNewPhone(phone, quantity);
                }
            }
            System.out.println("Дані магазину успішно завантажено з файлу: " + fileName);
        } catch (IOException e) {
            System.out.println("Файл " + fileName + " не знайдено. Створено порожній магазин.");
        } catch (Exception e) {
            System.out.println("Помилка обробки JSON-файлу: " + e.getMessage());
        }

        return store;
    }

    public static void saveToJson(Store store, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();

        for (StoreItem item : store.getItems()) {
            JsonObject jsonObject = gson.toJsonTree(item.getPhone()).getAsJsonObject();
            jsonObject.addProperty("classType", item.getPhone().getClass().getSimpleName());
            jsonObject.addProperty("quantity", item.getQuantity());
            jsonArray.add(jsonObject);
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(jsonArray, writer);
            System.out.println("Актуальний стан магазину збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}