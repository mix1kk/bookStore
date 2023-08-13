package com.example.bookStore.Utils.AuthorExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorErrorResponse {
    private String message;
    private long timestamp;

    public AuthorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
