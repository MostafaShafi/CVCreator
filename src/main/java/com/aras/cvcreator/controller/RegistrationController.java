package com.aras.cvcreator.controller;

import com.aras.cvcreator.exceptions.PasswordWrongFormatException;
import com.aras.cvcreator.model.*;
import com.aras.cvcreator.model.utilModels.*;
import com.aras.cvcreator.service.*;
import com.aras.cvcreator.service.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@SessionAttributes({"user", "person"})
public class RegistrationController {

    //<editor-fold desc="Services and Utils">
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private LocationServiceImpl locationService;
    @Autowired
    private IndustryServiceImpl industryService;
    @Autowired
    private DegreeServiceImpl degreeService;
    @Autowired
    private FieldServiceImpl fieldService;
    @Autowired
    private ExperienceServiceImpl experienceService;
    @Autowired
    private EducationServiceImpl educationService;
    @Autowired
    private GenderServiceImpl genderService;
    @Autowired
    private ExperienceTypeServiceImpl experienceTypeService;
    @Autowired
    private WorkLocationTypeServiceImpl workLocationTypeService;
    @Autowired
    private SkillServiceImpl skillService;
    @Autowired
    private StatusServiceImpl statusService;
    //</editor-fold>

    @ModelAttribute("user")
    public Users user() {
        return new Users();
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person();
    }

    @GetMapping("/signin")
    public String signIn(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("countryList", locationService.findAll());
        return "signin";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") Users user, Model model, RedirectAttributes redirectAttributes) {
        try {
            if (userService.registerAuthorise(user.getEmail(), user.getPassword())) {
                userService.create(user);
                model.addAttribute("error", "");
                model.addAttribute("user", user);
                model.addAttribute("username", user.getFullName());
            }
        } catch (PasswordWrongFormatException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("countryList", locationService.findAll());
            return "redirect:/signin";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "There is a user with this email!");
            redirectAttributes.addFlashAttribute("countryList", locationService.findAll());
            return "redirect:/signin";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "There is a problem with sign in~ please try again.");
            redirectAttributes.addFlashAttribute("countryList", locationService.findAll());
            return "redirect:/signin";
        }
        return "welcome";
    }

    @GetMapping("/personProfile")
    public String personProfile(@ModelAttribute("user") Users user, @ModelAttribute("image") MultipartFile image, Model model) {
        user.setType(UserType.Employee);
        userService.save(user);

        Person person = personService.findByUserId(user.getId());
        if (person == null) {
            person = new Person();
            person.setUsers(user);
            personService.save(person);
        }

        model.addAttribute("user", user);
        model.addAttribute("person", person);
        model.addAttribute("experience", new Experience());
        model.addAttribute("education", new Education());
        model.addAttribute("skill", new Skill());

        model.addAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        model.addAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        model.addAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));

        model.addAttribute("skillList", skillService.findAll());
        model.addAttribute("countryList", locationService.findAll());
        model.addAttribute("industryList", industryService.findAll());
        model.addAttribute("relatedIndustryList", industryService.findAll());
        model.addAttribute("degreeList", degreeService.findAll());
        model.addAttribute("fieldList", fieldService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("genderList", genderService.findAll());
        model.addAttribute("experienceTypeList", experienceTypeService.findAll());
        model.addAttribute("locationTypeList", workLocationTypeService.findAll());
        return "personProfile";
    }
}
