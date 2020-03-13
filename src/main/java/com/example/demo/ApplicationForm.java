package com.example.demo;

import org.springframework.data.annotation.Id;

public class ApplicationForm {

    @Id
    private String id;

    private String email;

    private String education;

    private String name;

    private String surname;

    private String tel;

    private String answerContract;

    private String answerTime;

    private String answerMotivation;

    private String answerExperience;

    private String answerInfoAbout;

    public ApplicationForm() {
    }

    public ApplicationForm(String email, String education, String name, String surname, String tel,
                           String answerContract, String answerTime, String answerMotivation, String answerExperience,
                           String answerInfoAbout) {
        this.email = email;
        this.education = education;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.answerContract = answerContract;
        this.answerTime = answerTime;
        this.answerMotivation = answerMotivation;
        this.answerExperience = answerExperience;
        this.answerInfoAbout = answerInfoAbout;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTel() {
        return tel;
    }

    public String getAnswerContract() {
        return answerContract;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public String getAnswerMotivation() {
        return answerMotivation;
    }

    public String getAnswerExperience() {
        return answerExperience;
    }

    public String getAnswerInfoAbout() {
        return answerInfoAbout;
    }
}