package com.inside.hacaton_04_2023.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "history_card")
public class HistoryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_card", referencedColumnName = "id")
    private Card card;

    @OneToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public HistoryCard() {}
    public HistoryCard(Card card, User user) {
        this.card = card;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public Card getCard() {
        return card;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "HistoryCard{" +
                "id=" + id +
                ", card=" + card +
                ", user=" + user +
                '}';
    }
}
