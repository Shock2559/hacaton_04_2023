package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.OtklickCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtklickCardDAO extends CrudRepository<OtklickCard, Long> {
}
