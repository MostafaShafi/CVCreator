package com.aras.cvcreator.service;

import com.aras.cvcreator.model.ExperienceType;
import com.aras.cvcreator.repository.ExperienceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExperienceTypeServiceImpl implements ExperienceTypeService {

    @Autowired
    private ExperienceTypeRepository repository;

    @Override
    public ExperienceType save(ExperienceType type) {
        return repository.save(type);
    }

    @Override
    public ExperienceType findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ExperienceType> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
