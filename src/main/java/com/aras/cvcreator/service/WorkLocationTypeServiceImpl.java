package com.aras.cvcreator.service;

import com.aras.cvcreator.model.WorkLocationType;
import com.aras.cvcreator.repository.WorkLocationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkLocationTypeServiceImpl implements WorkLocationTypeService {

    @Autowired
    private WorkLocationTypeRepository repository;

    @Override
    @Transactional
    public WorkLocationType save(WorkLocationType workLocationType) {
        return repository.save(workLocationType);
    }

    @Override
    @Transactional
    public WorkLocationType findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<WorkLocationType> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
