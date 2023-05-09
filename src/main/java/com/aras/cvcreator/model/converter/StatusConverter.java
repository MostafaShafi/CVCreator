package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Status;
import com.aras.cvcreator.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Autowired
    private StatusService service;

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        return service.findById(id);
    }
}
