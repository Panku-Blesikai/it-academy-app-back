package com.example.demo.admin;

public class IncorrectEmailException extends Exception{
    public IncorrectEmailException(String message) {
        super(message);
    }
}
