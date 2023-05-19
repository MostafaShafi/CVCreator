package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Proficiency;

import java.util.List;

public interface ProficiencyService {
    List<Proficiency> findAll();

    Proficiency findById(Integer id);

    Proficiency save(Proficiency proficiency);

    Proficiency findByName(String name);

    void delete(Proficiency proficiency);
}
