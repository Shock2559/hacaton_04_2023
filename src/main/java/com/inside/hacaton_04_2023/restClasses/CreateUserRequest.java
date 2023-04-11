package com.inside.hacaton_04_2023.restClasses;

public class CreateUserRequest {

    private String name;
    private String email;
    private String numberPhone;
    private boolean employer;
    private String login;
    private String password;

    public CreateUserRequest() {}
    public CreateUserRequest(String name, String email,
                             String numberPhone, boolean employer,
                             String login, String password) {
        this.name = name;
        this.email = email;
        this.numberPhone = numberPhone;
        this.employer = employer;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public boolean isEmployer() {
        return employer;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setEmployer(boolean employer) {
        this.employer = employer;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
