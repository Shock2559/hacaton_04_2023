package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.HistoryCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryCardDAO extends CrudRepository<HistoryCard, Long> {

    @Query("select h from HistoryCard h where h.card.user.id = :id and h.card.statusOpen = 2")
    List<HistoryCard> getWorkHistoryCard(@Param("id") int id);

    @Query("select h from HistoryCard h where h.card.user.id = :id and h.card.statusOpen = 3")
    List<HistoryCard> getEndHistoryCard(@Param("id") int id);

    @Query("select h from HistoryCard h where h.user.id = :id and h.card.statusOpen = 3")
    List<HistoryCard> getMyEndHistoryCard(@Param("id") int id);
}
