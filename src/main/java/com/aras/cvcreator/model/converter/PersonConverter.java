package com.aras.cvcreator.model.converter;

import com.aras.cvcreator.model.Person;
import com.aras.cvcreator.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PersonConverter implements AttributeConverter<Person, Integer> {

    @Autowired
    private PersonServiceImpl personService;

    @Override
    public Integer convertToDatabaseColumn(Person person) {
        return person.getId();
    }

    @Override
    public Person convertToEntityAttribute(Integer integer) {
        return personService.findById(integer);
    }
}
