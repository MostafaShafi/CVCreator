package com.aras.cvcreator.model;

import com.aras.cvcreator.model.converter.DegreeConverter;
import com.aras.cvcreator.model.converter.FieldConverter;
import com.aras.cvcreator.model.converter.PersonConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "school")
    private String school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degreeId")
    @Convert(converter = DegreeConverter.class)
    private Degree degree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fieldId")
    @Convert(converter = FieldConverter.class)
    private Field field;

    @Column(name = "startDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @Column(name = "grade")
    private double grade;

    @Column(name = "activities")
    private String activities;

    @Column(name = "description")
    private String description;

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

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String university) {
        this.activities = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startYear) {
        this.startDate = startYear;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endYear) {
        this.endDate = endYear;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDegreeName() {
        return this.degree != null ? this.degree.getName() : null;
    }

    public String getFieldName() {
        return this.field != null ? this.field.getName() : null;
    }
}
