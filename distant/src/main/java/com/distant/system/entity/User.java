package com.distant.system.entity;

public class User {
    private int userID;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String role;



    public User() {

    }

    public User(String login, String password, String name, String surname, String role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
