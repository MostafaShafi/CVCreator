package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Industry;

import java.util.List;

public interface IndustryService {
    List<Industry> findAll();

    Industry findById(Integer id);

    Industry save(Industry industry);
}
