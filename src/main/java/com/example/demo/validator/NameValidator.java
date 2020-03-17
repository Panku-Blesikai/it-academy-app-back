package com.example.demo.validator;

public class NameValidator extends Validator<String> {
    @Override
    public void validate(String attribute, String message) {
        if (isStringEmpty(attribute)) {
            throw new ValidationException("Input field is empty");
        }

        attribute = attribute.trim();

        checkEveryNameInString(attribute, message);
    }

    private void checkEveryNameInString(String attribute, String errorMessage) {
        String[] words = attribute.split(" ");
        for (String word : words) {
            if (!doesStringContainLetters(word)) {
                throw new ValidationException(errorMessage);
            }
        }
    }
}
