package com.example.demo.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Admin {

    @Id
    private String id;
    private String login;
    private String password;
    private String name;
    private String surname;

    public Admin() {
    }

    public Admin(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Admin(String login, String password) {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
