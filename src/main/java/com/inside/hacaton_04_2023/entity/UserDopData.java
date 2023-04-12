package com.inside.hacaton_04_2023.entity;

import com.inside.hacaton_04_2023.restClasses.UserSkillsResponse;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "user_dop_data")
public class UserDopData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "post")
    private String post;

    @Column(name = "img")
    private String img;

    @Column(name = "location")
    private String location;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Transient
    public List<UserSkillsResponse> userSkills;

    public UserDopData(){};
    public UserDopData(String post, String img, String location, User user) {
        this.post = post;
        this.img = img;
        this.location = location;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public String getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDopData{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", img='" + img + '\'' +
                ", location='" + location + '\'' +
                ", user=" + user +
                '}';
    }
}
