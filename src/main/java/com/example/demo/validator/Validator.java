package com.example.demo.validator;

public abstract class Validator<T> {
    public abstract void validate(T attribute, String message);

    public boolean isStringEmpty(String string) {
        return string.trim().length() == 0;
    }

    public boolean doesStringContainLetters(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
