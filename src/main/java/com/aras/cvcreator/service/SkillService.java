package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();

    Skill findById(Integer id);

    Skill save(Skill skill);

    List<Skill> findSkillsByPersonId(List<Integer> ids);

    Skill findByName(String name);
}
