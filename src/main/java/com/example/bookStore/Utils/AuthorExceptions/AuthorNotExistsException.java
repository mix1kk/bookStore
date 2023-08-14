package com.example.bookStore.Utils.AuthorExceptions;

public class AuthorNotExistsException extends RuntimeException{
    public AuthorNotExistsException(String message) {
        super(message);
    }
}