package com.example.demo.validator;

import com.example.demo.applicationForm.ApplicationForm;

public class ApplicationFormValidator {
    public void validate(ApplicationForm applicationForm) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.validate(applicationForm.getName());

        SurnameValidator surnameValidator = new SurnameValidator();
        surnameValidator.validate(applicationForm.getSurname());

        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(applicationForm.getEmail());

        TelNumberValidator telNumberValidator = new TelNumberValidator();
        telNumberValidator.validate(applicationForm.getTel());

        EducationValidator educationValidator = new EducationValidator();
        educationValidator.validate(applicationForm.getEducation());

        FreeTimeActivityValidator freeTimeActivityValidator = new FreeTimeActivityValidator();
        freeTimeActivityValidator.validate(applicationForm.getAnswerFreeTimeActivity());

        ThreePartAgreementValidator threePartAgreementValidator = new ThreePartAgreementValidator();
        threePartAgreementValidator.validate(applicationForm.getAnswerThreePartAgreement());

        Available14To18Validator available14To18Validator = new Available14To18Validator();
        available14To18Validator.validate(applicationForm.getAnswerAvailable14To18());

        MotivationValidator motivationValidator = new MotivationValidator();
        motivationValidator.validate(applicationForm.getAnswerMotivation());

        ExperienceValidator experienceValidator = new ExperienceValidator();
        experienceValidator.validate(applicationForm.getAnswerExperience());

        InfoAboutAcademyValidator infoAboutAcademyValidator = new InfoAboutAcademyValidator();
        infoAboutAcademyValidator.validate(applicationForm.getAnswerInfoAboutAcademy());
    }
}
