package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SkillRepository extends CrudRepository<Skill, Integer> {

    @Query(value = "select *, 0 as clazz_ from skill where name like :name", nativeQuery = true)
    public Skill findByName(@Param("name") String name);
}
