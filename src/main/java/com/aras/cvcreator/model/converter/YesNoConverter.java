package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.utilModels.YesNo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YesNoConverter implements AttributeConverter<YesNo, String> {

    @Override
    public String convertToDatabaseColumn(YesNo gender) {
        return gender.getName();
    }

    @Override
    public YesNo convertToEntityAttribute(String name) {
        return YesNo.getFromName(name);
    }
}
