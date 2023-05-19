package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Proficiency;
import com.aras.cvcreator.repository.ProficiencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProficiencyServiceImpl implements ProficiencyService {

    @Autowired
    private ProficiencyRepository repository;

    @Override
    public List<Proficiency> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Proficiency findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Proficiency save(Proficiency proficiency) {
        return repository.save(proficiency);
    }

    @Override
    public Proficiency findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Proficiency proficiency) {
        repository.delete(proficiency);
    }
}
