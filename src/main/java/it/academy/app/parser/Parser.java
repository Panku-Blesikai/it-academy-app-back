package it.academy.app.parser;

import it.academy.app.form.ApplicationForm;

public class Parser {
    public ApplicationForm parse(ApplicationForm applicationForm) {
        applicationForm.setName(deleteSpaces(applicationForm.getName()));
        applicationForm.setSurname(deleteSpaces(applicationForm.getSurname()));
        applicationForm.setTel(telNumberFormat(applicationForm.getTel()));
        applicationForm.setEducation(deleteSpaces(applicationForm.getEducation()));
        applicationForm.setAnswerFreeTimeActivity(deleteSpaces(applicationForm.getAnswerFreeTimeActivity()));
        applicationForm.setAnswerThreePartAgreement(deleteSpaces(applicationForm.getAnswerThreePartAgreement()));
        applicationForm.setAnswerAvailable14To18(deleteSpaces(applicationForm.getAnswerAvailable14To18()));
        applicationForm.setAnswerMotivation(deleteSpaces(applicationForm.getAnswerMotivation()));
        applicationForm.setAnswerExperience(deleteSpaces(applicationForm.getAnswerExperience()));
        applicationForm.setAnswerInfoAboutAcademy(applicationForm.getAnswerInfoAboutAcademy());
        return applicationForm;
    }

    private String deleteSpaces(String attribute) {
        String[] words = attribute.split(" ");
        String formattedString = "";
        for (int i = 0; i < words.length; ++i) {
            if (!words[i].equals(" ")) {
                formattedString = formattedString + words[i] + " ";
            }
        }
        return formattedString;
    }

    private String telNumberFormat(String attribute) {
        return attribute.substring(0, 5) + "-" + attribute.substring(5, 7) + "-" + attribute.substring(7);
    }
}
