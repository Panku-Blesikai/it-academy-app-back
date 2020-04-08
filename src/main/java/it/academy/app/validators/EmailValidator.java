package it.academy.app.validators;

import it.academy.app.exception.ValidationException;

import java.util.regex.Pattern;

public class EmailValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();
        checkIfEmailFormIsValid(attribute);
    }

    private void checkIfEmailFormIsValid(String email) {
        String emailRegex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (!pat.matcher(email).matches()) {
            throw new ValidationException(ErrorMessages.invalidEmailFormat);
        }
    }
}
