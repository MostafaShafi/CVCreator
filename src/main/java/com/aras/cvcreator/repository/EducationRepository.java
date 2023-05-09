package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Education;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends CrudRepository<Education, Integer> {

    @Query(value = "select *, 0 as clazz_ from education where personId = :id", nativeQuery = true)
    public Iterable<Education> findEducationsByPersonId(@Param("id") Integer id);
}
