package com.aras.cvcreator.service;

import com.aras.cvcreator.model.ExperienceType;

import java.util.List;

public interface ExperienceTypeService {
    ExperienceType save(ExperienceType type);

    ExperienceType findById(Integer id);

    List<ExperienceType> findAll();
}
