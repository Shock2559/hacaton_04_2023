package com.inside.hacaton_04_2023.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "namr")
    private String namr;

    public Test() {}

    public int getId() {
        return id;
    }

    public String getNamr() {
        return namr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamr(String namr) {
        this.namr = namr;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", namr='" + namr + '\'' +
                '}';
    }
}
