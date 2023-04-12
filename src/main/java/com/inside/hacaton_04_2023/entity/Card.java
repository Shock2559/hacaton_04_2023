package com.inside.hacaton_04_2023.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "post")
    private String post;

    @Column(name = "description_one")
    private String descriptionOne;

    @Column(name = "description_two")
    private String descriptionTwo;

    @Column(name = "status_open")
    private int statusOpen;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private User user;

    public Card() {}
    public Card(String name, String post, String descriptionOne, String descriptionTwo, int statusOpen, User user) {
        this.name = name;
        this.post = post;
        this.descriptionOne = descriptionOne;
        this.descriptionTwo = descriptionTwo;
        this.statusOpen = statusOpen;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }
    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }
    public void setStatusOpen(int statusOpen) {
        this.statusOpen = statusOpen;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPost() {
        return post;
    }
    public String getDescriptionOne() {
        return descriptionOne;
    }
    public String getDescriptionTwo() {
        return descriptionTwo;
    }
    public int getStatusOpen() {
        return statusOpen;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", descriptionOne='" + descriptionOne + '\'' +
                ", descriptionTwo='" + descriptionTwo + '\'' +
                ", statusOpen=" + statusOpen +
                ", user=" + user +
                '}';
    }
}
