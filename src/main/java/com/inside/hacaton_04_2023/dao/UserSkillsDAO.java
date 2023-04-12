package com.inside.hacaton_04_2023.dao;

import com.inside.hacaton_04_2023.entity.UserSkills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillsDAO extends CrudRepository<UserSkills, Long> {
}
