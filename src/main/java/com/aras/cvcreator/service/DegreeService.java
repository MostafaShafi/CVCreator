package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Degree;

import java.util.List;

public interface DegreeService {
    List<Degree> findAll();

    Degree save(Degree degree);

    Degree findById(Integer id);
}
