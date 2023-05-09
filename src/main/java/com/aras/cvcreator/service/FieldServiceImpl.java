package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Field;
import com.aras.cvcreator.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository repository;

    @Override
    public List<Field> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Field findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Field save(Field field) {
        return repository.save(field);
    }
}
