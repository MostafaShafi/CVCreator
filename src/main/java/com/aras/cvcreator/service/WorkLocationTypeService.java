package com.aras.cvcreator.service;

import com.aras.cvcreator.model.WorkLocationType;

import java.util.List;

public interface WorkLocationTypeService {
    WorkLocationType save(WorkLocationType workLocationType);

    WorkLocationType findById(Integer id);

    List<WorkLocationType> findAll();
}
