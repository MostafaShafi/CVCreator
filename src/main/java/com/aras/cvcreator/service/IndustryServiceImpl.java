package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Industry;
import com.aras.cvcreator.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryRepository repository;

    //@Transactional
    @Override
    public List<Industry> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //@Transactional
    @Override
    public Industry findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Industry save(Industry industry) {
        return repository.save(industry);
    }
}
