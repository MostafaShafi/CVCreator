package com.aras.cvcreator.controller;

import com.aras.cvcreator.exceptions.PasswordWrongFormatException;
import com.aras.cvcreator.model.*;
import com.aras.cvcreator.service.*;
import com.aras.cvcreator.service.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes({"user", "person"})
public class LoginController {

    //<editor-fold desc="Services and Utils">
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private DegreeService degreeService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private ExperienceTypeService experienceTypeService;
    @Autowired
    private WorkLocationTypeService workLocationTypeService;
    @Autowired
    private String profileImageDir;
    @Autowired
    private String backgroundImageDir;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private BaseLanguageService baseLanguageService;
    @Autowired
    private ProficiencyService proficiencyService;
    //</editor-fold>

    @ModelAttribute("user")
    public Users user() {
        return new Users();
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person();
    }

    @GetMapping("/")
    public String home(@ModelAttribute("user") Users user, Model model) {
        List<Person> personList = personService.findAll();

        //<editor-fold desc="Load image to profiles image and background image directory">
        Path filepath = null;
        File file = null;
        for (Person person: personList) {
            if (person.getProfileImage() != null) {
                filepath = Paths.get(profileImageDir,
                        person.getProfileImage().getName().concat(".").concat(person.getProfileImage().getType()));
                file = filepath.toFile();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try (OutputStream os = Files.newOutputStream(filepath)) {
                    os.write(person.getProfileImage().getImage());
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "Uploading profile image was not successful!");
                    return "redirect:/personProfile";
                }
            }
        }
        //</editor-fold>

        model.addAttribute("personList", personList);
        return "login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") Users user) {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Users user, @ModelAttribute("image") MultipartFile image,
                        Model model, RedirectAttributes redirectAttributes) {
        try {
            user = userService.loginAuthorise(user.getEmail(), user.getPassword());
            if (user != null) {
                Person person = personService.findByUserId(user.getId());

                //<editor-fold desc="Load image to profiles image and background image directory">
                if (person.getProfileImage() != null) {
                    Path filepath = Paths.get(profileImageDir,
                            person.getProfileImage().getName().concat(".").concat(person.getProfileImage().getType()));
                    File file = filepath.toFile();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try (OutputStream os = Files.newOutputStream(filepath)) {
                        os.write(person.getProfileImage().getImage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        redirectAttributes.addFlashAttribute("error", "Uploading profile image was not successful!");
                        return "redirect:/";
                    }
                }

                if (person.getBackgroundImage() != null) {
                    Path filepath = Paths.get(backgroundImageDir,
                            person.getBackgroundImage().getName().concat(".").concat(person.getBackgroundImage().getType()));
                    File file = filepath.toFile();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try (OutputStream os = Files.newOutputStream(filepath)) {
                        os.write(person.getBackgroundImage().getImage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        redirectAttributes.addFlashAttribute("error", "Uploading background image was not successful!");
                        return "redirect:/personProfile";
                    }
                }
                //</editor-fold>

                model.addAttribute("user", user);
                model.addAttribute("person", person);

                model.addAttribute("experience", new Experience());
                model.addAttribute("education", new Education());
                model.addAttribute("skill", new Skill());
                model.addAttribute("language", new Language());
                model.addAttribute("image", new byte[]{});
                model.addAttribute("expertise", new Skill());

                model.addAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
                model.addAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
                model.addAttribute("skillObjList", skillService.findSkillsByPersonId(
                        commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
                model.addAttribute("expertiseObjList", skillService.findSkillsByPersonId(
                        commonUtils.splitStringOfIntegers(person.getExpertises(), ", ")));
                model.addAttribute("langObjList", languageService.findLanguagesByPersonId(person.getId()));

                model.addAttribute("skillList", skillService.findAll());
                model.addAttribute("languageList", baseLanguageService.findAll());
                model.addAttribute("proficiencyList", proficiencyService.findAll());
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
        } catch (PasswordWrongFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Password is wrong!");
            return "redirect:/";
        } catch (UsernameNotFoundException e) {
            redirectAttributes.addFlashAttribute("error",
                    "There is no user with this email! You can sign in by using below link!");
            return "redirect:/";
        } catch (BadCredentialsException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/";
        }
        return "login";
    }
}
