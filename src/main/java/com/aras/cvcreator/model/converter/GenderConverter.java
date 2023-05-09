package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Gender;
import com.aras.cvcreator.service.GenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Autowired
    private GenderServiceImpl service;
    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender.getId();
    }

    @Override
    public Gender convertToEntityAttribute(Integer id) {
        return service.findById(id);
    }
}
