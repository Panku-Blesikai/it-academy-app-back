package com.example.demo.validator;

public abstract class Validator<T> {
    public abstract void validate(T attribute);

    public boolean isStringEmpty(String string) {
        return string.trim().length() == 0;
    }

    public void checkIfFieldIsEmpty(String data) {
        if (isStringEmpty(data)) {
            throw new ValidationException(ErrorMessages.fieldIsEmpty);
        }
    }

    public void doesStringContainLetters(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                throw new ValidationException(ErrorMessages.illegalCharactersUsed);
            }
        }
    }

    public void doesStringContainNumbers(String string) {
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                throw new ValidationException(ErrorMessages.illegalCharactersUsed);
            }
        }
    }

    public void doesNotExceedLimit(String string, int n) {
        if (string.length() >= n) {
            throw new ValidationException(ErrorMessages.invalidInputSize);
        }
    }
}
