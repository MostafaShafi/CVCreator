package com.aras.cvcreator.model;

import com.aras.cvcreator.model.converter.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity(name = "Experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "companyName")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "typeId")
    @Convert(converter = ExperienceTypeConverter.class)
    private ExperienceType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industryId")
    @Convert(converter = IndustryConverter.class)
    private Industry industry;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "locationType")
    @Convert(converter = WorkLocationTypeConverter.class)
    private WorkLocationType workLocationType;

    @Column(name = "isUntilWork")
    private Boolean isUntilWork;

    @Column(name = "startDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(name = "endDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String jobName) {
        this.description = jobName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExperienceType getType() {
        return type;
    }

    public void setType(ExperienceType type) {
        this.type = type;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsUntilWork() {
        return isUntilWork;
    }

    public void setIsUntilWork(Boolean isUntilWork) {
        this.isUntilWork = isUntilWork;
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

    public WorkLocationType getWorkLocationType() {
        return workLocationType;
    }

    public void setWorkLocationType(WorkLocationType workLocationType) {
        this.workLocationType = workLocationType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getIndustryName() {
        return this.industry != null ? this.industry.getName() : null;
    }

    public String getPeriod() {
        if (this.startDate != null) {
            LocalDateTime st = LocalDateTime.ofInstant(this.startDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime ed = LocalDateTime.ofInstant(this.endDate != null ? this.endDate.toInstant() : (new Date()).toInstant(),
                    ZoneId.systemDefault());

            long year = ChronoUnit.YEARS.between(st, ed);
            long month = ChronoUnit.MONTHS.between(st, ed) % 12;
            return (year > 1 ? (year + " yrs ") : (year == 1 ? (year + " yr ") : "")) +
                    (month > 1 ? (month + " mos") : (month == 1 ? (month + "mo") : ""));
        }
        return new String();
    }

    public boolean getIsDescriptionEmpty() {
        return this.description == null || this.description.isEmpty() ? true : false;
    }

    public String getDescSummary() {
        return this.description != null && !this.description.isEmpty() && this.description.length() > 100 ?
                this.description.substring(0, 100).concat(" ...") :
                this.description;
    }
}
