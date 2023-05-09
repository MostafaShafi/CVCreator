package com.aras.cvcreator.model;

import com.aras.cvcreator.model.utilModels.LocationType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "locationType")
    @Enumerated(EnumType.ORDINAL)
    private LocationType locationType;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Person> persons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
