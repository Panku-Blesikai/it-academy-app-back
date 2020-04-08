package it.academy.app.models;

import org.springframework.data.annotation.Id;

public class Admin {

    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;


    public Admin(String id, String username, String password, String name, String surname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
