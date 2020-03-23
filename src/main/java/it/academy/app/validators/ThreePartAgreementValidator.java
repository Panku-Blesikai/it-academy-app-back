package it.academy.app.validators;

public class ThreePartAgreementValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);

        doesNotExceedLimit(attribute, AnswerLength.longQuestionAnswer.getValue());
    }
}
