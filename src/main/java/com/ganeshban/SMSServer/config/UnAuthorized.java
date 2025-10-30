package com.ganeshban.smsserver.config;

public class UnAuthorized extends Exception {
    private final String message;

    public UnAuthorized(String message) {
        super(message);
        this.message = message;
    }
}
