package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Gender;
import com.aras.cvcreator.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository repository;

    @Override
    @Transactional
    public Gender save(Gender gender) {
        return repository.save(gender);
    }

    @Override
    @Transactional
    public Gender findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Gender> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
