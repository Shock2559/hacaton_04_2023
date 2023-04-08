package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.Test;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDAO extends CrudRepository<Test, Long> {

    @Query("select t from Test t where t.id = :id")
    Test myTestById(@Param("id") int id);

}
