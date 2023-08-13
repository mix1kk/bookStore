package com.example.bookStore.Utils.BookExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookErrorResponse {
    private String message;
    private long timestamp;

    public BookErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
