package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Status;

import java.util.List;

public interface StatusService {
    Status save(Status status);

    Status findById(Integer id);

    List<Status> findAll();
}
