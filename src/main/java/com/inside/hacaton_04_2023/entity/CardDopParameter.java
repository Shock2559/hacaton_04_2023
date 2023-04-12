package com.inside.hacaton_04_2023.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "card_dop_parameter")
public class CardDopParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_card", referencedColumnName = "id")
    private Card card;

    public CardDopParameter() {}
    public CardDopParameter(String title, String description, Card card) {
        this.title = title;
        this.description = description;
        this.card = card;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "CardDopParameter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", card=" + card +
                '}';
    }
}
