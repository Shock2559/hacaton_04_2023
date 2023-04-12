package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDAO  extends CrudRepository<Card, Long> {
}
