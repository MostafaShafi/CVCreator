package com.aras.cvcreator.model;

import com.aras.cvcreator.model.converter.PersonConverter;
import com.aras.cvcreator.model.converter.ProficiencyConverter;

import javax.persistence.*;

@Entity(name = "Language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProficiencyId")
    @Convert(converter = ProficiencyConverter.class)
    private Proficiency proficiency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    @Convert(converter = PersonConverter.class)
    private Person person;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Proficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(Proficiency proficiency) {
        this.proficiency = proficiency;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
