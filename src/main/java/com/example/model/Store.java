package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    
    // Сортування за ціною (зростання) через анонімний клас Comparator
    public ArrayList<StoreItem> getItemsSortedByPrice() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Collections.sort(sortedList, new Comparator<StoreItem>() {
            @Override
            public int compare(StoreItem item1, StoreItem item2) {
                return Double.compare(item1.getPhone().getPrice(), item2.getPhone().getPrice());
            }
        });
        return sortedList;
    }

    // Сортування за ОЗП (спадання) через анонімний клас Comparator
    public ArrayList<StoreItem> getItemsSortedByRamDesc() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Collections.sort(sortedList, new Comparator<StoreItem>() {
            @Override
            public int compare(StoreItem item1, StoreItem item2) {
                return Integer.compare(item2.getPhone().getRamGb(), item1.getPhone().getRamGb());
            }
        });
        return sortedList;
    }

    // Сортування за брендом (алфавіт) через анонімний клас Comparator
    public ArrayList<StoreItem> getItemsSortedByBrand() {
        ArrayList<StoreItem> sortedList = new ArrayList<>(items);
        Collections.sort(sortedList, new Comparator<StoreItem>() {
            @Override
            public int compare(StoreItem item1, StoreItem item2) {
                return item1.getPhone().getBrand().compareToIgnoreCase(item2.getPhone().getBrand());
            }
        });
        return sortedList;
    }

    public Store(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва магазину не може бути порожньою.");
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
     * Додає телефон у магазин. Якщо товар вже є, підсумовує кількість.
     */
    public void addNewPhone(Phone ph, int quantity) {
        if (ph == null) {
            throw new IllegalArgumentException("Об'єкт Phone не може бути null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість повинна бути більше 0.");
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