package com.inside.hacaton_04_2023.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "user_skills")
public class UserSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_user_dop_data", referencedColumnName = "id")
    private UserDopData userDopData;

    public UserSkills() {}
    public UserSkills(String name, UserDopData userDopData) {
        this.name = name;
        this.userDopData = userDopData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserDopData(UserDopData userDopData) {
        this.userDopData = userDopData;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserDopData getUserDopData() {
        return userDopData;
    }

    @Override
    public String toString() {
        return "UserSkills{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userDopData=" + userDopData +
                '}';
    }
}
