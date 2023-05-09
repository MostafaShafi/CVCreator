package com.aras.cvcreator.repository;


import com.aras.cvcreator.model.WorkLocationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WorkLocationTypeRepository extends CrudRepository<WorkLocationType, Integer> {
}
