package com.inside.hacaton_04_2023.restClasses;

import java.util.List;

public class UpdateUserRequest {
    private int id;
    private String name;
    private String email;
    private String numberPhone;
    private boolean employer;
    private String login;
    private String password;
    private String post;
    private String img;
    private String location;
    private List<String> userSkills;

    public UpdateUserRequest() {}
    public UpdateUserRequest(String name, String email,
                             String numberPhone, boolean employer,
                             String login, String password,
                             String post, String img,
                             String location, List<String> userSkills) {
        this.name = name;
        this.email = email;
        this.numberPhone = numberPhone;
        this.employer = employer;
        this.login = login;
        this.password = password;
        this.post = post;
        this.img = img;
        this.location = location;
        this.userSkills = userSkills;
    }

    public int getId() {
        return id;
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

    public String getPost() {
        return post;
    }

    public String getLocation() {
        return location;
    }

    public String getImg() {
        return img;
    }

    public List<String> getUserSkills() {
        return userSkills;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPost(String post) {
        this.post = post;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUserSkills(List<String> userSkills) {
        this.userSkills = userSkills;
    }
}
