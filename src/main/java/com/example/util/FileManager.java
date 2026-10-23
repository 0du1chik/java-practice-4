package com.example.util;

import com.example.model.*;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Сервісний клас для читання та запису списку телефонів у JSON-файл за допомогою Gson.
 */
public class FileManager {

    public static ArrayList<Phone> loadFromJson(String fileName) {
        ArrayList<Phone> phones = new ArrayList<>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(fileName)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (!jsonObject.has("classType")) {
                    continue;
                }

                String classType = jsonObject.get("classType").getAsString();
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
                    phones.add(phone);
                }
            }
            System.out.println("Дані успішно завантажено з файлу: " + fileName);
        } catch (IOException e) {
            System.out.println("Файл " + fileName + " не знайдено або він порожній. Створено новий порожній список.");
        } catch (Exception e) {
            System.out.println("Помилка обробки JSON-файлу: " + e.getMessage());
        }

        return phones;
    }

    public static void saveToJson(ArrayList<Phone> phones, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();

        for (Phone phone : phones) {
            JsonObject jsonObject = gson.toJsonTree(phone).getAsJsonObject();
            jsonObject.addProperty("classType", phone.getClass().getSimpleName());
            jsonArray.add(jsonObject);
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(jsonArray, writer);
            System.out.println("Актуальний стан збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}