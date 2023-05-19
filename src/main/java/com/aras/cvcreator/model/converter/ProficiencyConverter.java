package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Proficiency;
import com.aras.cvcreator.service.ProficiencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProficiencyConverter implements AttributeConverter<Proficiency, Integer> {

    @Autowired
    private ProficiencyServiceImpl service;
    @Override
    public Integer convertToDatabaseColumn(Proficiency proficiency) {
        return proficiency.getId();
    }

    @Override
    public Proficiency convertToEntityAttribute(Integer id) {
        return service.findById(id);
    }
}
