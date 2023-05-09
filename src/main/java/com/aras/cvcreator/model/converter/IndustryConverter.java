package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Industry;
import com.aras.cvcreator.service.IndustryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IndustryConverter implements AttributeConverter<Industry, Integer> {

    @Autowired
    private IndustryServiceImpl industryService;

    @Override
    public Integer convertToDatabaseColumn(Industry location) {
        return location.getId();
    }

    @Override
    public Industry convertToEntityAttribute(Integer integer) {
        return industryService.findById(integer);
    }
}
