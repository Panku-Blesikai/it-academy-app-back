package it.academy.app.form;

import org.springframework.data.annotation.Id;

public class ApplicationForm {

    @Id
    private String id;

    private String email;

    private String education;

    private String name;

    private String surname;

    private String tel;

    private String answerFreeTimeActivity;

    private String answerThreePartAgreement;

    private String answerAvailable14To18;

    private String answerMotivation;

    private String answerExperience;

    private String answerInfoAboutAcademy;

    private String status;

    private String  dateTime;

    public ApplicationForm() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAnswerFreeTimeActivity() {
        return answerFreeTimeActivity;
    }

    public void setAnswerFreeTimeActivity(String answerFreeTimeActivity) {
        this.answerFreeTimeActivity = answerFreeTimeActivity;
    }

    public String getAnswerThreePartAgreement() {
        return answerThreePartAgreement;
    }

    public void setAnswerThreePartAgreement(String answerThreePartAgreement) {
        this.answerThreePartAgreement = answerThreePartAgreement;
    }

    public String getAnswerAvailable14To18() {
        return answerAvailable14To18;
    }

    public void setAnswerAvailable14To18(String answerAvailable14To18) {
        this.answerAvailable14To18 = answerAvailable14To18;
    }

    public String getAnswerMotivation() {
        return answerMotivation;
    }

    public void setAnswerMotivation(String answerMotivation) {
        this.answerMotivation = answerMotivation;
    }

    public String getAnswerExperience() {
        return answerExperience;
    }

    public void setAnswerExperience(String answerExperience) {
        this.answerExperience = answerExperience;
    }

    public String getAnswerInfoAboutAcademy() {
        return answerInfoAboutAcademy;
    }

    public void setAnswerInfoAboutAcademy(String answerInfoAboutAcademy) {
        this.answerInfoAboutAcademy = answerInfoAboutAcademy;
    }
}