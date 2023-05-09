package com.aras.cvcreator.controller;

import com.aras.cvcreator.model.*;
import com.aras.cvcreator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    //<editor-fold desc="Services and Utils">
    @Autowired
    private LocationServiceImpl locationService;
    @Autowired
    private IndustryServiceImpl industryService;
    @Autowired
    private DegreeServiceImpl degreeService;
    @Autowired
    private FieldServiceImpl fieldService;
    @Autowired
    private SkillServiceImpl skillService;
    @Autowired
    private StatusServiceImpl statusService;
    @Autowired
    private GenderServiceImpl genderService;
    @Autowired
    private ExperienceTypeServiceImpl experienceTypeService;
    @Autowired
    private WorkLocationTypeServiceImpl workLocationTypeService;
    //</editor-fold>

    @PostMapping("/saveCountry")
    public String saveCountry(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{
                "Afghanistan", "Åland Islands", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica",
                "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
                "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Caribbean Netherlands",
                "Bosnia & Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic",
                "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo - Brazzaville",
                "Congo - Kinshasa", "Cook Islands", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Curaçao", "Cyprus", "Czechia",
                "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
                "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Islas Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
                "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Heard & McDonald Islands", "Vatican City", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
                "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan",
                "Kazakhstan", "Kenya", "Kiribati", "North Korea", "South Korea", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
                "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "North Macedonia",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania",
                "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
                "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "Curaçao", "New Caledonia", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan",
                "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
                "Portugal", "Puerto Rico", "Qatar", "Réunion", "Romania", "Russia", "Rwanda", "St. Barthélemy", "St. Helena", "St. Kitts & Nevis",
                "St. Lucia", "St. Martin", "St. Pierre & Miquelon", "St. Vincent & Grenadines", "Samoa", "San Marino", "São Tomé & Príncipe",
                "Saudi Arabia", "Senegal", "Serbia", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten", "Slovakia",
                "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia & South Sandwich Islands", "South Sudan", "Spain",
                "Sri Lanka", "Sudan", "Suriname", "Svalbard & Jan Mayen", "Eswatini", "Sweden", "Switzerland", "Syria", "Taiwan",
                "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey",
                "Turkmenistan", "Turks & Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
                "United States", "U.S. Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "British Virgin Islands",
                "U.S. Virgin Islands", "Wa llis & Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};

        Location location = null;
        for (int i = 0; i < ss.length; i++) {
            location = new Location();
            location.setName(ss[i]);
            locationService.saveCountry(location);
        }
        return "home";
    }

    @PostMapping("/saveIndustry")
    public String saveIndustry(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Network & Security", "Software Engineering", "IT", "AI", "Marketing"};

        Industry industry = null;
        for (int i = 0; i < ss.length; i++) {
            industry = new Industry();
            industry.setName(ss[i]);
            industryService.save(industry);
        }
        return "home";
    }

    @PostMapping("/saveDegree")
    public String saveDegree(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Doctor of Science", "Doctor of Medicine", "Doctor of Arts", "Doctor of Laws", "Doctor of Pharmacy",
                "Doctor of Philosophy", "Master of Science", "Master of Engineering", "Master of Arts", "Master of Laws",
                "Master of Architecture", "Bachelor of Science", "Bachelor of Engineering", "Bachelor of Arts", "Bachelor of Laws",
                "Bachelor of Architecture", "Associate of Science", "Associate of Arts", "High School Diploma",};

        Degree degree = null;
        for (int i = 0; i < ss.length; i++) {
            degree = new Degree();
            degree.setName(ss[i]);
            degreeService.save(degree);
        }
        return "home";
    }

    @PostMapping("/saveField")
    public String saveField(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Computer Engineering", "Computer Science", "IT", "AI", "Marketing", "Network"};

        Field field = null;
        for (int i = 0; i < ss.length; i++) {
            field = new Field();
            field.setName(ss[i]);
            fieldService.save(field);
        }
        return "home";
    }

    @PostMapping("/saveSkill")
    public String saveSkill(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Java Script", "Html", "Css", "Python", "Android", "Spring Core", "Spring Boot", "Spring MVC",
                "Spring Security", "Spring Data", "Spring Cloud", "Hibernate", "Thymeleaf", "Programing", "Web Programing",};

        Skill skill = null;
        for (int i = 0; i < ss.length; i++) {
            skill = new Skill();
            skill.setName(ss[i]);
            skillService.save(skill);
        }
        return "home";
    }

    @PostMapping("/saveStatus")
    public String saveStatus(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Working", "Open To Work"};

        Status status = null;
        for (int i = 0; i < ss.length; i++) {
            status = new Status();
            status.setName(ss[i]);
            statusService.save(status);
        }
        return "home";
    }

    @PostMapping("/saveGender")
    public String saveGender(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Male", "Female", "Other", "Rather not say"};

        Gender gender = null;
        for (int i = 0; i < ss.length; i++) {
            gender = new Gender();
            gender.setName(ss[i]);
            genderService.save(gender);
        }
        return "home";
    }

    @PostMapping("/saveExperienceType")
    public String saveExperienceType(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"Full Time", "Part Time", "Project", "Freelance"};

        ExperienceType experienceType = null;
        for (int i = 0; i < ss.length; i++) {
            experienceType = new ExperienceType();
            experienceType.setName(ss[i]);
            experienceTypeService.save(experienceType);
        }
        return "home";
    }

    @PostMapping("/saveWorkLocationType")
    public String saveWorkLocationType(@ModelAttribute("user") Users user) {
        String[] ss = new String[]{"On Site", "Remote"};

        WorkLocationType workLocationType = null;
        for (int i = 0; i < ss.length; i++) {
            workLocationType = new WorkLocationType();
            workLocationType.setName(ss[i]);
            workLocationTypeService.save(workLocationType);
        }
        return "home";
    }
}
