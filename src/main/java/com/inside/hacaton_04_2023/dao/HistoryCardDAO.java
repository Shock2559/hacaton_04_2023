package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.HistoryCard;
import org.springframework.data.repository.CrudRepository;

public interface HistoryCardDAO extends CrudRepository<HistoryCard, Long> {
}
