package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface FieldRepository extends CrudRepository<Field, Integer> {
}
