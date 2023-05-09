package com.aras.cvcreator.model;

import com.aras.cvcreator.model.converter.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "genderId")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "countryId")
    @Convert(converter = LocationConverter.class)
    private Location country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "currentPosition")
    private String currentPosition;

    @ManyToOne
    @JoinColumn(name = "industryId")
    @Convert(converter = IndustryConverter.class)
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "statusId")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @Column(name = "address")
    private String address;

    @Column(name = "summary")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "backgroundImage")
    private Image backgroundImage;

    @ManyToOne
    @JoinColumn(name = "profileImage")
    private Image profileImage;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Education> educations;

    @Column(name = "skills")
    private String skills;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Location getCountry() {
        return country;
    }

    public void setCountry(Location country) {
        this.country = country;
    }

    public String getLocationName() {
        return this.country != null ? this.country.getName() : null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skillList) {
        this.skills = skillList;
    }
}
