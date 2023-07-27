package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Location;
import com.aras.cvcreator.model.utilModels.LocationType;

import java.util.List;

public interface LocationService {
    Location save(Location location);

    List<Location> findByType(LocationType type);

    void saveCountry(Location location);

    List<Location> findAll();

    Location findById(Long id);
}
