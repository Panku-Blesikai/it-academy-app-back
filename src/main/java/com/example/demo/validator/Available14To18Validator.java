package com.example.demo.validator;

public class Available14To18Validator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);

        doesNotExceedLimit(attribute, AnswerLength.longQuestionAnswer.getValue());
    }
}
