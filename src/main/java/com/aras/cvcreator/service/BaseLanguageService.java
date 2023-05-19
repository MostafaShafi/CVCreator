package com.aras.cvcreator.service;

import com.aras.cvcreator.model.BaseLanguage;

import java.util.List;

public interface BaseLanguageService {
    List<BaseLanguage> findAll();

    BaseLanguage findById(Integer id);

    List<BaseLanguage> findLanguagesByPersonId(List<Integer> ids);

    BaseLanguage save(BaseLanguage language);

    BaseLanguage findByName(String name);

    void delete(BaseLanguage language);
}
