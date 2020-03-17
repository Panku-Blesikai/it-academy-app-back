package com.example.demo.validator;

import com.example.demo.ApplicationForm;

public class ApplicationFormValidator {
    public void validate(ApplicationForm applicationForm) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.validate(applicationForm.getName(), "Invalid name input");
    }
}
