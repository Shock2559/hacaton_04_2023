package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.Card;
import com.inside.hacaton_04_2023.entity.OtklickCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtklickCardDAO extends CrudRepository<OtklickCard, Long> {

    List<OtklickCard> getOtklickCardByCard(@Param("card")Card card);
}
