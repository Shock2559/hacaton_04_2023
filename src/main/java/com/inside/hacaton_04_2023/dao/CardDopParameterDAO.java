package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.CardDopParameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDopParameterDAO extends CrudRepository<CardDopParameter, Long> {
}
