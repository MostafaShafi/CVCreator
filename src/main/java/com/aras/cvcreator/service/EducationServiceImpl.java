package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Education;
import com.aras.cvcreator.model.Experience;
import com.aras.cvcreator.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository repository;

    @Override
    public List<Education> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).
                sorted(Comparator.comparing(Education::getStartDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public Education findById(Integer id) {
        return repository.findById(id.intValue()).orElse(null);
    }

    @Override
    public Education save(Education education) {
        return repository.save(education);
    }

    @Override
    public List<Education> findEducationsByPersonId(Integer id) {
        return StreamSupport.stream(repository.findEducationsByPersonId(id).spliterator(), false).
        sorted(Comparator.comparing(Education::getStartDate).reversed()).collect(Collectors.toList());
    }
}
