package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Location;
import com.aras.cvcreator.model.utilModels.LocationType;
import com.aras.cvcreator.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    //@Transactional
    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

    //@Transactional
    @Override
    public void saveCountry(Location location) {
        location.setLocationType(LocationType.Country);
        save(location);
    }

    //@Transactional
    @Override
    public List<Location> findByType(LocationType type) {
        return null;
    }

    //@Transactional
    @Override
    public List<Location> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //@Transactional
    @Override
    public Location findById(Long id) {
        return this.repository.findById(id.intValue()).orElse(null);
    }
}
