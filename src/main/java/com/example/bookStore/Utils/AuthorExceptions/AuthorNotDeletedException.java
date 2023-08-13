package com.example.bookStore.Utils.AuthorExceptions;

public class AuthorNotDeletedException extends RuntimeException {
    public AuthorNotDeletedException(String message) {
        super(message);
    }
}
