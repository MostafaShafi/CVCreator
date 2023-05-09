package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Degree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends CrudRepository<Degree, Integer> {
}
