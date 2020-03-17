package com.example.demo.validator;

public class TelNumberValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);

        checkNumberByNumberFormat(attribute);
    }

    private void checkNumberByNumberFormat(String number) {
        if (number.length() == 12) {
            checkLongNumberFormat(number);
        } else if (number.length() == 9) {
            checkShortNumberFormat(number);
        } else {
            throw new ValidationException(ErrorMessages.invalidTelNumberLength);
        }
    }

    private void checkLongNumberFormat(String number) {
        if (number.charAt(0) != '+') {
            throw new ValidationException(ErrorMessages.invalidTelNumberFormat);
        }
        doesStringContainNumbers(number.substring(1));
    }

    private void checkShortNumberFormat(String number) {
        doesStringContainNumbers(number);
    }
}
