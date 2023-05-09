package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Field;

import java.util.List;

public interface FieldService {
    List<Field> findAll();

    Field findById(Integer id);

    Field save(Field field);
}
