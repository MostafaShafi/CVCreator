package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Gender;

import java.util.List;

public interface GenderService {
    Gender save(Gender gender);

    Gender findById(Integer id);

    List<Gender> findAll();
}
