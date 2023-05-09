package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Person;
import com.aras.cvcreator.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public List<Person> findAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Person findByUserId(Integer id) {
        return personRepository.findByUserId(id);
    }
}
