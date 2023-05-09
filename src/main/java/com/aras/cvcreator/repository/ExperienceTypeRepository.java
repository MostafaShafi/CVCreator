package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.ExperienceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ExperienceTypeRepository extends CrudRepository<ExperienceType, Integer> {
}
