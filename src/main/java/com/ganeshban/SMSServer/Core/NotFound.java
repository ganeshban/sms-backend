package com.ganeshban.SMSServer.Core;

public class NotFound extends Exception {
   public String message;

    public NotFound(String message) {
        this.message = message;
    }
}
