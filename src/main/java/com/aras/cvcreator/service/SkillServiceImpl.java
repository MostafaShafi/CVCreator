package com.aras.cvcreator.service;

import com.aras.cvcreator.model.Education;
import com.aras.cvcreator.model.Skill;
import com.aras.cvcreator.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    @Override
    public List<Skill> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Skill findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill skill) {
        return repository.save(skill);
    }

    @Override
    public List<Skill> findSkillsByPersonId(List<Integer> ids) {//StreamSupport.stream(repository.findSkillsByPersonId(id).spliterator(), false).collect(Collectors.toList());
        return StreamSupport.stream(repository.findAllById(ids).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Skill findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Skill skill) {
        repository.delete(skill);
    }
}
