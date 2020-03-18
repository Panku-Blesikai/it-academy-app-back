package com.example.demo.validator;

public enum ErrorMessages {
    fieldIsEmpty("Field is empty"),
    illegalCharactersUsed("Illegal field input"),
    moreThanTwoSurnames("Invalid surname input, more than two surnames"),
    invalidEmailFormat("Email format is invalid"),
    invalidTelNumberLength("Telephone number length is incorrect"),
    invalidTelNumberFormat("Telephone number format is invalid");

    private String error;

    ErrorMessages(String s) {
        error = s;
    }
}
