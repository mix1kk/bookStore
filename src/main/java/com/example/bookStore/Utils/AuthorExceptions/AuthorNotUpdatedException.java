package com.example.bookStore.Utils.AuthorExceptions;

public class AuthorNotUpdatedException extends RuntimeException{
    public AuthorNotUpdatedException(String message) {
        super(message);
    }
}