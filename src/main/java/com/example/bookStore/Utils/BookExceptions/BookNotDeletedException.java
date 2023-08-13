package com.example.bookStore.Utils.BookExceptions;

public class BookNotDeletedException extends RuntimeException {
    public BookNotDeletedException(String message) {
        super(message);
    }
}
