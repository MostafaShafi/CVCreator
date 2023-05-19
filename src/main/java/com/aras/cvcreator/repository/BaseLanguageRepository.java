package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.BaseLanguage;
import com.aras.cvcreator.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BaseLanguageRepository extends CrudRepository<BaseLanguage, Integer> {

    @Query(value = "select *, 0 as clazz_ from BaseLanguage where name like :name", nativeQuery = true)
    public BaseLanguage findByName(@Param("name") String name);
}
