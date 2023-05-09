package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.ExperienceType;
import com.aras.cvcreator.service.ExperienceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ExperienceTypeConverter implements AttributeConverter<ExperienceType, Integer> {

    @Autowired
    private ExperienceTypeServiceImpl service;

    @Override
    public Integer convertToDatabaseColumn(ExperienceType experienceType) {
        return experienceType.getId();
    }

    @Override
    public ExperienceType convertToEntityAttribute(Integer id) {
        return service.findById(id);
    }
}
