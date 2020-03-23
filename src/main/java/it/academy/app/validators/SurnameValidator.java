package it.academy.app.validators;

import it.academy.app.exception.ValidationException;
import it.academy.app.shared.ErrorMessages;

//Room for improvement: Regex could be used for validation
public class SurnameValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);
        doesNotExceedLimit(attribute, AnswerLength.shortInfoAnswer.getValue());

        doesStringContainOnlyLettersSpacesOrDash(attribute);

        checkNumberOfWords(attribute);
    }

    private void checkNumberOfWords(String attribute) {
        String[] words = attribute.split(" ");
        if (words.length > 2) {
            throw new ValidationException(ErrorMessages.moreThanTwoSurnames);
        }
    }

    public void doesStringContainOnlyLettersSpacesOrDash(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!(Character.isLetter(c) || c == '-' || c == ' ')) {
                throw new ValidationException(ErrorMessages.illegalCharactersUsed);
            }
        }
    }

}
