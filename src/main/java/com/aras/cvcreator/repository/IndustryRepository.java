package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Industry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IndustryRepository extends CrudRepository<Industry, Integer> {
}
