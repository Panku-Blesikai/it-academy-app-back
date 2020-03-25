package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.ErrorMessages;

import java.util.regex.Pattern;

public class TelNumberValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);

        checkNumberByNumberFormat(attribute);
    }

    private void checkNumberByNumberFormat(String number) {
        String phoneNumberRegex = "^[+]370[0-9][0-9]{2}[0-9]{5}$";
        System.out.println(number);
        Pattern pat = Pattern.compile(phoneNumberRegex);

        if (!pat.matcher(number).matches()) {
            throw new ValidationException(ErrorMessages.invalidTelNumberFormat);
        }
    }
}
