package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.WorkLocationType;
import com.aras.cvcreator.service.WorkLocationTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WorkLocationTypeConverter implements AttributeConverter<WorkLocationType, Integer> {

    @Autowired
    private WorkLocationTypeServiceImpl service;

    @Override
    public Integer convertToDatabaseColumn(WorkLocationType type) {
        return type.getId();
    }

    @Override
    public WorkLocationType convertToEntityAttribute(Integer id) {
        return service.findById(id);
    }
}
