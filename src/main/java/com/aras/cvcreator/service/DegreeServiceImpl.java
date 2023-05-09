package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Degree;
import com.aras.cvcreator.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    private DegreeRepository repository;

    @Override
    public List<Degree> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Degree save(Degree degree) {
        return repository.save(degree);
    }

    @Override
    public Degree findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
