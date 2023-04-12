package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.UserDopData;
import com.inside.hacaton_04_2023.entity.UserSkills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillsDAO extends CrudRepository<UserSkills, Long> {

    List<UserSkills> findUserSkillsByUserDopData(@Param("userDopData")UserDopData userDopData);

}
