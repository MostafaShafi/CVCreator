package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Degree;
import com.aras.cvcreator.service.DegreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DegreeConverter implements AttributeConverter<Degree, Integer> {

    @Autowired
    private DegreeServiceImpl degreeService;

    @Override
    public Integer convertToDatabaseColumn(Degree location) {
        return location.getId();
    }

    @Override
    public Degree convertToEntityAttribute(Integer integer) {
        return degreeService.findById(integer);
    }
}
