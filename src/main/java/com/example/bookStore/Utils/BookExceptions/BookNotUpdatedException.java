package com.example.bookStore.Utils.BookExceptions;

public class BookNotUpdatedException extends RuntimeException{
    public BookNotUpdatedException(String message) {
        super(message);
    }
}