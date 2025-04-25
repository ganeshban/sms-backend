package com.ganeshban.SMSServer.config;

public class NotFound extends Exception {
   public String message;

    public NotFound(String message) {
        super(message);
        this.message = message;
    }
}
