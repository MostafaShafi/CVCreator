package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Experience;

import java.util.List;

public interface ExperienceService {
    List<Experience> findAll();

    Experience findById(Long id);

    Experience save(Experience experience);

    List<Experience> findExperiencesByPersonId(Integer personId);
}
