package it.academy.app.validators;

public class EducationValidator extends Validator<String> {
    @Override
    public void validate(String attribute) {
        attribute = attribute.trim();

        checkIfFieldIsEmpty(attribute);
    }
}
