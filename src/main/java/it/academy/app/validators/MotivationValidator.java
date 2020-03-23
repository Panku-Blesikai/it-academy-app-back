package it.academy.app.validators;

public class MotivationValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);

        doesNotExceedLimit(attribute, AnswerLength.longQuestionAnswer.getValue());
    }
}
