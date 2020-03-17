package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ApplicationForm {

    @Id
    private ObjectId id;

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

    public ObjectId getId() {
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

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAnswerContract(String answerContract) {
        this.answerContract = answerContract;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public void setAnswerMotivation(String answerMotivation) {
        this.answerMotivation = answerMotivation;
    }

    public void setAnswerExperience(String answerExperience) {
        this.answerExperience = answerExperience;
    }

    public void setAnswerInfoAbout(String answerInfoAbout) {
        this.answerInfoAbout = answerInfoAbout;
    }
}