package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ImageRepository extends CrudRepository<Image, Integer> {

    @Query(value = "select *, 0 as clazz_ from image where name like :name", nativeQuery = true)
    Optional<Image> findByName(@Param("name") String name);
}
