package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.UserDopData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDopDataDAO extends CrudRepository<UserDopData, Long> {
}
