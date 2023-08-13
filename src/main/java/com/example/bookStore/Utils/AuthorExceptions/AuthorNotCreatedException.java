package com.example.bookStore.Utils.AuthorExceptions;

public class AuthorNotCreatedException extends RuntimeException{
    public AuthorNotCreatedException(String message) {
        super(message);
    }
}