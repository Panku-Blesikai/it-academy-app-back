package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.ErrorMessages;

//Room for improvement: Regex could be used for validation
public class NameValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {

        attribute = attribute.trim();

        if (attribute.equals("")) {
            throw new ValidationException(ErrorMessages.fieldIsEmpty);
        }

        checkEveryNameInString(attribute);
    }

    private void checkEveryNameInString(String attribute) {
        String[] words = attribute.split(" ");
        for (String word : words) {
            doesStringContainLetters(word);
        }
    }
}
