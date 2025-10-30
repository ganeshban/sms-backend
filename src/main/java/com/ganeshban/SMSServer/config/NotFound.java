package com.ganeshban.smsserver.config;

public class NotFound extends Exception {

    private final String message;

    public NotFound(String message) {
        super(message);
        this.message = message;
    }
}



