package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatusRepository extends CrudRepository<Status, Integer> {
}
