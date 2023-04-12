package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.Card;
import com.inside.hacaton_04_2023.entity.CardDopParameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDopParameterDAO extends CrudRepository<CardDopParameter, Long> {

    List<CardDopParameter> findCardDopParameterByCard(@Param("card") Card card);
}
