package it.academy.app.validators;

import it.academy.app.models.ApplicationForm;

public class ApplicationFormValidator {
    public void validate(ApplicationForm applicationForm) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.validate(applicationForm.getName());

        SurnameValidator surnameValidator = new SurnameValidator();
        surnameValidator.validate(applicationForm.getSurname());

        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(applicationForm.getEmail());

        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.validate(applicationForm.getPhone());
    }
}
