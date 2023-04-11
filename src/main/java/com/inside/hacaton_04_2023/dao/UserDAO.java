package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.LoginAndPassword;
import com.inside.hacaton_04_2023.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    @Query("select u from User u where u.loginAndPassword = :loginAndPassword")
    User getUserByLoginAndPassword(@Param("loginAndPassword") LoginAndPassword loginAndPassword);

    User getUserById(@Param("id") int id);
}
