package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Field;
import com.aras.cvcreator.service.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FieldConverter implements AttributeConverter<Field, Integer> {

    @Autowired
    private FieldServiceImpl fieldService;

    @Override
    public Integer convertToDatabaseColumn(Field field) {
        return field.getId();
    }

    @Override
    public Field convertToEntityAttribute(Integer integer) {
        return fieldService.findById(integer);
    }
}
