package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Status;
import com.aras.cvcreator.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;

    @Override
    @Transactional
    public Status save(Status status) {
        return repository.save(status);
    }

    @Override
    @Transactional
    public Status findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Status> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
