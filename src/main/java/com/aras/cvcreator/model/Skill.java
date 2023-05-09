package com.aras.cvcreator.model;

import javax.persistence.*;

@Entity(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    @Convert(converter = PersonConverter.class)
    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> person;*/

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

    /*public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }*/
}
