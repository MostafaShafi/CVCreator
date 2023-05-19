package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Language;
import com.aras.cvcreator.model.Skill;

import java.util.List;

public interface LanguageService {
    List<Language> findAll();

    Language findById(Integer id);

    List<Language> findLanguagesByPersonId(Integer id);

    Language save(Language language);

    Language findByName(String name);

    void delete(Language language);
}
