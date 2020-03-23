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
        String phoneNumberRegex = "^([+][0-9]{11}|[0-9]{9})$";
        System.out.println(number);
        Pattern pat = Pattern.compile(phoneNumberRegex);

        if (!pat.matcher(number).matches()) {
            throw new ValidationException(ErrorMessages.invalidTelNumberFormat);
        }
    }
}
