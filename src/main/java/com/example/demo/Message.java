package com.example.demo;

import org.springframework.data.annotation.Id;

public class Message {

    @Id
    private String id;

    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}