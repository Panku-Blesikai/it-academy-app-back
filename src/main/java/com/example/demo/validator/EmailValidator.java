package com.example.demo.validator;

import java.util.regex.Pattern;

public class EmailValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        isStringEmpty(attribute);

        checkIfEmailFormIsValid(attribute);
    }

    private void checkIfEmailFormIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        if (!pat.matcher(email).matches()) {
            throw new ValidationException(ErrorMessages.invalidEmailFormat);
        }
    }
}
