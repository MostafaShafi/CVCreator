package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Query(value = "select * from Location where locationType like =:type", nativeQuery = true)
    public List<Location> findByType(@Param("type") int type);
}
