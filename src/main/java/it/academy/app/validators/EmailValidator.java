package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.ErrorMessages;

import java.util.regex.Pattern;

public class EmailValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        isStringEmpty(attribute);
        doesNotExceedLimit(attribute, AnswerLength.shortInfoAnswer.getValue());

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
