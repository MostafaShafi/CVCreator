package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Education;

import java.util.List;

public interface EducationService {
    List<Education> findAll();

    Education findById(Integer id);

    Education save(Education education);

    List<Education> findEducationsByPersonId(Integer id);

    void delete(Education education);
}
