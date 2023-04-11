package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.LoginAndPassword;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAndPasswordDAO extends CrudRepository<LoginAndPassword, Long> {

    @Query("select l from LoginAndPassword l where l.login = :login and l.password = :password")
    LoginAndPassword getLoginAndPasswordByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    LoginAndPassword getLoginAndPasswordByLogin(@Param("login") String login);
}
