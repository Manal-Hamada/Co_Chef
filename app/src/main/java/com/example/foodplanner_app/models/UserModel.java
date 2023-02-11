package com.example.foodplanner_app.models;

public class UserModel {
    private String username;
    private String mail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserModel(){}
    public UserModel(String username, String mail) {
        this.username = username;
        this.mail = mail;
    }
}
