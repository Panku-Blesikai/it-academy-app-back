package it.academy.app.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApplicationForm {
    private final int shortInfoAnswer = 256;
    private final int longQuestionAnswer = 1024;

    @Id
    private String id;

    @NotNull
    @NotEmpty
    @Size(max = shortInfoAnswer)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = shortInfoAnswer)
    private String education;

    @NotNull
    @NotEmpty
    @Size(max = shortInfoAnswer)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = shortInfoAnswer)
    private String surname;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String freeTimeActivity;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String threePartAgreement;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String available14To18;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String motivation;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String experience;

    @NotNull
    @NotEmpty
    @Size(max = longQuestionAnswer)
    private String infoAboutAcademy;

    @NotNull
    @NotEmpty
    private String status;

    private String  dateTime;

    private String idHash;


    public ApplicationForm() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIdHash() {
        return idHash;
    }

    public void setIdHash(String idHash) {
        this.idHash = idHash;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFreeTimeActivity() {
        return freeTimeActivity;
    }

    public void setFreeTimeActivity(String freeTimeActivity) {
        this.freeTimeActivity = freeTimeActivity;
    }

    public String getThreePartAgreement() {
        return threePartAgreement;
    }

    public void setThreePartAgreement(String threePartAgreement) {
        this.threePartAgreement = threePartAgreement;
    }

    public String getAvailable14To18() {
        return available14To18;
    }

    public void setAvailable14To18(String available14To18) {
        this.available14To18 = available14To18;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getInfoAboutAcademy() {
        return infoAboutAcademy;
    }

    public void setInfoAboutAcademy(String infoAboutAcademy) {
        this.infoAboutAcademy = infoAboutAcademy;
    }
}