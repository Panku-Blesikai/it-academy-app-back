package com.example.demo.validator;

public class ExperienceValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);
    }
}
