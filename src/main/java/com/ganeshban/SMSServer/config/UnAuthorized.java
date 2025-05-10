package com.ganeshban.smsserver.config;

public class UnAuthorized extends Exception {
    public String message;

    public UnAuthorized(String message) {
        super(message);
        this.message = message;
    }
}
