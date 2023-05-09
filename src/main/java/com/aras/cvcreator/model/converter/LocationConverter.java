package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Location;
import com.aras.cvcreator.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocationConverter implements AttributeConverter<Location, Integer> {

    @Autowired
    private LocationServiceImpl locationService;

    @Override
    public Integer convertToDatabaseColumn(Location location) {
        return location.getId();
    }

    @Override
    public Location convertToEntityAttribute(Integer integer) {
        return locationService.findById((long) integer);
    }
}
