package com.example.model;

import com.example.exception.InvalidFieldValueException;
import com.example.exception.ObjectNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

/**
 * Клас-контейнер Store (Магазин), що підтримує агрегацію товарів та їх кількості.
 */
public class Store {
    private String name;
    private ArrayList<StoreItem> items;

    public ArrayList<StoreItem> getSortedItems() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Collections.sort(sortedList);
        return sortedList;
    }
    
// Сортування за ціною (зростання) за допомогою лямбда-виразу
    public ArrayList<StoreItem> getItemsSortedByPrice() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Comparator<StoreItem> priceComparator = (item1, item2) ->
                Double.compare(item1.getPhone().getPrice(), item2.getPhone().getPrice());
        Collections.sort(sortedList, priceComparator);
        return sortedList;
    }

    // Сортування за ОЗП (спадання) за допомогою лямбда-виразу
    public ArrayList<StoreItem> getItemsSortedByRamDesc() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Comparator<StoreItem> ramDescComparator = (item1, item2) ->
                Integer.compare(item2.getPhone().getRamGb(), item1.getPhone().getRamGb());
        Collections.sort(sortedList, ramDescComparator);
        return sortedList;
    }

    // Сортування за брендом (алфавіт) за допомогою лямбда-виразу
    public ArrayList<StoreItem> getItemsSortedByBrand() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Comparator<StoreItem> brandComparator = (item1, item2) ->
                item1.getPhone().getBrand().compareToIgnoreCase(item2.getPhone().getBrand());
        Collections.sort(sortedList, brandComparator);
        return sortedList;
    }

    public StoreItem findByUuid(UUID targetUuid) {
        if (targetUuid == null) {
            return null;
        }
        for (StoreItem item : items) {
            if (targetUuid.equals(item.getPhone().getUuid())) {
                return item;
            }
        }
        return null;
    }

    public Store(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidFieldValueException("Назва магазину не може бути порожньою.");
        }
        this.name = name.trim();
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<StoreItem> getItems() {
        return items;
    }
    
    /**
     * Оновлює існуючий телефон у магазині на новий об'єкт.
     * Зберігає UUID попереднього об'єкта, якщо в нового він не заданий вручну,
     * та залишає поточну кількість на складі.
     */
    public boolean update(Phone existingObject, Phone newObject) {
        if (existingObject == null || newObject == null) {
            throw new InvalidFieldValueException("Об'єкти для оновлення не можуть бути null.");
        }
        for (StoreItem item : items) {
            if (item.getPhone().equals(existingObject)) {
                newObject.setUuid(existingObject.getUuid());
                item.setPhone(newObject);
                return true;
            }
        }
        throw new ObjectNotFoundException("Об'єкт '" + existingObject.getBrand() + " " + existingObject.getModel() + "' не знайдено для оновлення.");
    }

    /**
     * Видаляє позицію з магазину за об'єктом Phone.
     */
    public boolean delete(Phone existingObject) {
        if (existingObject == null) {
            throw new InvalidFieldValueException("Об'єкт для видалення не може бути null.");
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getPhone().equals(existingObject)) {
                items.remove(i);
                return true;
            }
        }
        throw new ObjectNotFoundException("Об'єкт '" + existingObject.getBrand() + " " + existingObject.getModel() + "' не знайдено для видалення.");
    }

    /**
     * Додає телефон у магазин. Якщо товар вже є, підсумовує кількість.
     */
    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null) {
            throw new InvalidFieldValueException("Об'єкт Phone не може бути null.");
        }
        if (quantity <= 0) {
            throw new InvalidFieldValueException("Кількість повинна бути більше 0.");
        }

        for (StoreItem item : items) {
            if (item.getPhone().equals(ph)) {
                item.addQuantity(quantity);
                return;
            }
        }
        items.add(new StoreItem(ph, quantity));
    }

    public ArrayList<StoreItem> findByBrand(String brand) {
        ArrayList<StoreItem> result = new ArrayList<>();
        if (brand == null) return result;

        for (StoreItem item : items) {
            if (item.getPhone().getBrand().equalsIgnoreCase(brand.trim())) {
                result.add(item);
            }
        }
        return result;
    }

    public ArrayList<StoreItem> findByMaxPrice(double maxPrice) {
        ArrayList<StoreItem> result = new ArrayList<>();
        for (StoreItem item : items) {
            if (item.getPhone().getPrice() <= maxPrice) {
                result.add(item);
            }
        }
        return result;
    }

    public ArrayList<StoreItem> findByMinRam(int minRam) {
        ArrayList<StoreItem> result = new ArrayList<>();
        for (StoreItem item : items) {
            if (item.getPhone().getRamGb() >= minRam) {
                result.add(item);
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}