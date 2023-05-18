package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Experience;
import com.aras.cvcreator.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository repository;

    @Override
    public List<Experience> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).
                sorted(Comparator.comparing(Experience::getStartDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public Experience findById(Integer id) {
        return repository.findById(id.intValue()).orElse(null);
    }

    @Override
    public Experience save(Experience experience) {
        return repository.save(experience);
    }

    @Override
    public List<Experience> findExperiencesByPersonId(Integer personId) {
        return StreamSupport.stream(repository.findExperiencesByPersonId(personId).spliterator(), false).
                sorted(Comparator.comparing(Experience::getStartDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public void delete(Experience experience) {
        repository.delete(experience);
    }
}
