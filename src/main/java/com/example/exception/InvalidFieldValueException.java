package com.example.exception;

/**
 * Виняток, що виникає при передачі некоректного значення поля або параметра.
 */
public class InvalidFieldValueException extends RuntimeException {
    public InvalidFieldValueException(String message) {
        super(message);
    }
}