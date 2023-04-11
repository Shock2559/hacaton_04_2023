package com.inside.hacaton_04_2023.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "employer")
    private boolean employer;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private LoginAndPassword loginAndPassword;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEmployer() {
        return employer;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public LoginAndPassword getLoginAndPassword() {
        return loginAndPassword;
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

    public void setEmployer(boolean employer) {
        this.employer = employer;
    }

    public void setLoginAndPassword(LoginAndPassword loginAndPassword) {
        this.loginAndPassword = loginAndPassword;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", employer=" + employer +
                ", loginAndPassword=" + loginAndPassword +
                '}';
    }
}
