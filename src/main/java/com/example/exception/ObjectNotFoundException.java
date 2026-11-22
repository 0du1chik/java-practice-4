package com.example.exception;

/**
 * Виняток, що виникає при спробі виконати операцію над об'єктом, якого немає в колекції.
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}