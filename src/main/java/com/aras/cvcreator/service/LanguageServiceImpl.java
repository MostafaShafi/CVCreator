package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Language;
import com.aras.cvcreator.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository repository;

    @Override
    public List<Language> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Language findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Language> findLanguagesByPersonId(Integer id) {
        return StreamSupport.stream(repository.findLanguagesByPersonId(id).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Language save(Language language) {
        return repository.save(language);
    }

    @Override
    public Language findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Language language) {
        repository.delete(language);
    }
}
