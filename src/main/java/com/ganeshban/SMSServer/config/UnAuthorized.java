package com.ganeshban.smsserver.config;

public class UnAuthorized extends Exception {

    public UnAuthorized(String message) {
        super(message);
    }
}
