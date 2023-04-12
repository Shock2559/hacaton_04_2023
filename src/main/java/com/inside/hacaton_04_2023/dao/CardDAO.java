package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDAO  extends CrudRepository<Card, Long> {

    Card getCardById(@Param("id") int id);

    @Query("select c from Card c where c.statusOpen = 1 and c.user.employer = false")
    List<Card> getCardForEmployer();

    @Query("select c from Card c where c.statusOpen = 1 and c.user.employer = true")
    List<Card> getCardForEmployee();

    @Query("select c from Card c where c.statusOpen = 1 and c.user.id = :id")
    List<Card> getStartCardById(@Param("id") int id);

}
