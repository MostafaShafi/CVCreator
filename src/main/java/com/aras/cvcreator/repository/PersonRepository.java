package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query(value = "select *, 0 as clazz_ from person where userId = :userId", nativeQuery = true)
    public Person findByUserId(@Param("userId") Integer userId);
}
