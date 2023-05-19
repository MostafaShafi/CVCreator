package com.aras.cvcreator.service;

import com.aras.cvcreator.model.BaseLanguage;
import com.aras.cvcreator.model.Language;
import com.aras.cvcreator.repository.BaseLanguageRepository;
import com.aras.cvcreator.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BaseLanguageServiceImpl implements BaseLanguageService {

    @Autowired
    private BaseLanguageRepository repository;

    @Override
    public List<BaseLanguage> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public BaseLanguage findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<BaseLanguage> findLanguagesByPersonId(List<Integer> ids) {
        return StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public BaseLanguage save(BaseLanguage language) {
        return repository.save(language);
    }

    @Override
    public BaseLanguage findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(BaseLanguage language) {
        repository.delete(language);
    }
}
