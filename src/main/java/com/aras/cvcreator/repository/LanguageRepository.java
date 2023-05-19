package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Education;
import com.aras.cvcreator.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LanguageRepository extends CrudRepository<Language, Integer> {

    @Query(value = "select *, 0 as clazz_ from language where name like :name", nativeQuery = true)
    public Language findByName(@Param("name") String name);

    @Query(value = "select *, 0 as clazz_ from language where personId = :id", nativeQuery = true)
    public Iterable<Language> findLanguagesByPersonId(@Param("id") Integer id);
}
