package com.example.bookStore.Utils.BookExceptions;

public class BookNotCreatedException extends RuntimeException{
    public BookNotCreatedException(String message) {
        super(message);
    }
}