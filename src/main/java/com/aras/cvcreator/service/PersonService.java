package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    List<Person> findAll();

    Person findById(Integer id);

    Person findByUserId(Integer id);
}
