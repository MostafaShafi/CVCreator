package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Experience;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    @Query(value = "select *, 0 as clazz_ from experience where personId = :id", nativeQuery = true)
    public Iterable<Experience> findExperiencesByPersonId(@Param("id") Integer id);
}
