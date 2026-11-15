package com.example.gui;

import com.example.model.*;
import com.example.util.FileManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.UUID;

public class MainApp extends Application {
    private static final String FILE_NAME = "input.json";
    private Store store;
    private ObservableList<String> shortItemsList;

    @Override
    public void start(Stage primaryStage) {
        store = FileManager.loadFromJson(FILE_NAME, "TechStore");
        shortItemsList = FXCollections.observableArrayList();
        refreshShortList();

        // --- Блок 1: Форма додавання об'єкта ---
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("SmartPhone", "KeypadPhone", "FoldableSmartPhone", "SecureKeypadPhone");
        typeBox.setValue("SmartPhone");

        TextField brandField = new TextField();
        brandField.setPromptText("Бренд (напр. Apple)");

        TextField modelField = new TextField();
        modelField.setPromptText("Модель (напр. iPhone 16)");

        TextField priceField = new TextField();
        priceField.setPromptText("Ціна ($)");

        TextField ramField = new TextField();
        ramField.setPromptText("ОЗП (ГБ)");

        TextField extraField = new TextField();
        extraField.setPromptText("ОС (для Smart) або Захист (для Secure)");

        Button addButton = new Button("Додати");
        Label addStatusLabel = new Label();

        addButton.setOnAction(e -> {
            try {
                String type = typeBox.getValue();
                String brand = brandField.getText().trim();
                String model = modelField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int ram = Integer.parseInt(ramField.getText().trim());
                String extra = extraField.getText().trim().isEmpty() ? "Standard" : extraField.getText().trim();

                Phone newPhone;
                switch (type) {
                    case "KeypadPhone":
                        newPhone = new KeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, true);
                        break;
                    case "FoldableSmartPhone":
                        newPhone = new FoldableSmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, extra, true);
                        break;
                    case "SecureKeypadPhone":
                        newPhone = new SecureKeypadPhone(brand, model, price, ram, PhoneType.FEATURE_PHONE, true, extra);
                        break;
                    default:
                        newPhone = new SmartPhone(brand, model, price, ram, PhoneType.SMARTPHONE, extra);
                        break;
                }

                store.addNewPhone(newPhone, 1);
                FileManager.saveToJson(store, FILE_NAME);
                refreshShortList();

                addStatusLabel.setText("Додано! UUID: " + newPhone.getUuid());
                brandField.clear();
                modelField.clear();
                priceField.clear();
                ramField.clear();
                extraField.clear();
            } catch (NumberFormatException ex) {
                addStatusLabel.setText("Помилка: ціна та ОЗП мають бути числами.");
            } catch (IllegalArgumentException ex) {
                addStatusLabel.setText("Помилка: " + ex.getMessage());
            }
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(8);
        formGrid.addRow(0, new Label("Тип:"), typeBox, new Label("Бренд:"), brandField);
        formGrid.addRow(1, new Label("Модель:"), modelField, new Label("Ціна ($):"), priceField);
        formGrid.addRow(2, new Label("ОЗП (ГБ):"), ramField, new Label("ОС / Захист:"), extraField);
        formGrid.add(addButton, 0, 3);
        formGrid.add(addStatusLabel, 1, 3, 3, 1);

        // --- Блок 2: Короткий список колекції ---
        ListView<String> listView = new ListView<>(shortItemsList);
        listView.setPrefHeight(180);

        // Автопідстановка UUID у поле пошуку при кліку по елементу списку (зручно для демонстрації)
        TextField searchUuidField = new TextField();
        searchUuidField.setPromptText("Введіть UUID для пошуку...");
        searchUuidField.setPrefWidth(320);

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.contains("UUID: ")) {
                String extractedUuid = newVal.substring(newVal.indexOf("UUID: ") + 6).trim();
                searchUuidField.setText(extractedUuid);
            }
        });

        // --- Блок 3: Пошук за UUID та повний вивід ---
        Button searchButton = new Button("Знайти");
        TextArea fullInfoArea = new TextArea();
        fullInfoArea.setEditable(false);
        fullInfoArea.setWrapText(true);
        fullInfoArea.setPrefHeight(90);

        searchButton.setOnAction(e -> {
            String input = searchUuidField.getText().trim();
            if (input.isEmpty()) {
                fullInfoArea.setText("Помилка: введіть UUID у поле пошуку.");
                return;
            }
            try {
                UUID targetUuid = UUID.fromString(input);
                StoreItem found = store.findByUuid(targetUuid);
                if (found != null) {
                    fullInfoArea.setText("Знайдено об'єкт:\n" + found.toString());
                } else {
                    fullInfoArea.setText("Результат: об'єкт із UUID [" + targetUuid + "] не знайдено.");
                }
            } catch (IllegalArgumentException ex) {
                fullInfoArea.setText("Помилка формату: введений рядок не є валідним UUID.");
            }
        });

        HBox searchBox = new HBox(10, new Label("UUID:"), searchUuidField, searchButton);

        VBox root = new VBox(12,
                new Label("=== Додавання нового об'єкта ==="),
                formGrid,
                new Separator(),
                new Label("=== Колекція об'єктів (Коротко + UUID) ==="),
                listView,
                new Separator(),
                new Label("=== Пошук повної інформації за UUID ==="),
                searchBox,
                fullInfoArea
        );
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 680, 560);
        primaryStage.setTitle("ЛР №16 — Phone Store (UUID + JavaFX GUI)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshShortList() {
        shortItemsList.clear();
        for (StoreItem item : store.getItems()) {
            shortItemsList.add(item.getPhone().toShortString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}