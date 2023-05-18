package com.aras.cvcreator.controller;

import com.aras.cvcreator.model.*;
import com.aras.cvcreator.service.*;
import com.aras.cvcreator.service.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes({"user", "person"})
public class PersonController {

    //<editor-fold desc="Services and Utils">
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private ExperienceServiceImpl experienceService;
    @Autowired
    private EducationServiceImpl educationService;
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
    private ImageServiceImpl imageService;
    @Autowired
    private StatusServiceImpl statusService;
    @Autowired
    private GenderServiceImpl genderService;
    @Autowired
    private ExperienceTypeServiceImpl experienceTypeService;
    @Autowired
    private WorkLocationTypeServiceImpl workLocationTypeService;
    @Autowired
    private String profileImageDir;
    @Autowired
    private String backgroundImageDir;
    //</editor-fold>

    //<editor-fold desc="Model attributes">
    @ModelAttribute("user")
    public Users user() {
        return new Users();
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person();
    }

    @ModelAttribute("skillList")
    public List<Skill> skillList() {
        return skillService.findAll();
    }

    @ModelAttribute("countryList")
    public List<Location> countryList() {
        return locationService.findAll();
    }

    @ModelAttribute("industryList")
    public List<Industry> industryList() {
        return industryService.findAll();
    }

    @ModelAttribute("relatedIndustryList")
    public List<Industry> relatedIndustryList() {
        return industryService.findAll();
    }

    @ModelAttribute("degreeList")
    public List<Degree> degreeList() {
        return degreeService.findAll();
    }

    @ModelAttribute("fieldList")
    public List<Field> fieldList() {
        return fieldService.findAll();
    }

    @ModelAttribute("statusList")
    public List<Status> statusList() {
        return statusService.findAll();
    }

    @ModelAttribute("genderList")
    public List<Gender> genderList() {
        return genderService.findAll();
    }

    @ModelAttribute("experienceTypeList")
    public List<ExperienceType> experienceTypeList() {
        return experienceTypeService.findAll();
    }

    @ModelAttribute("locationTypeList")
    public List<WorkLocationType> locationTypeList() {
        return workLocationTypeService.findAll();
    }
    //</editor-fold>

    //<editor-fold desc="Personal Info">
    @PostMapping("/saveInfo")
    private String savePersonalInfo(@ModelAttribute("person") Person person) {
        return "redirect:/personProfile";
    }

    @PostMapping("/updatePersonalInfo")
    private String updatePersonalInfo(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                      RedirectAttributes redirectAttributes) {
        personService.save(person);

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @GetMapping("/viewProfile/{id}")
    private String viewProfile(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Person person = personService.findById(id);

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
                    return "redirect:/";
                }
            }
            //</editor-fold>

            model.addAttribute("user", userService.getByPersonId(id));
            model.addAttribute("person", person);

            model.addAttribute("experience", new Experience());
            model.addAttribute("education", new Education());
            model.addAttribute("skill", new Skill());
            model.addAttribute("image", new byte[]{});

            model.addAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
            model.addAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
            model.addAttribute("skillObjList", skillService.findSkillsByPersonId(
                    commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
            return "viewPersonProfile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }
    //</editor-fold>
    //<editor-fold desc="Image">
    @PostMapping("/uploadProfileImage")
    private String uploadProfileImage(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                      RedirectAttributes redirectAttributes) {
        //<editor-fold desc="Save image in database(Image and Person table) and profiles image directory">
        Image oldProfileImage = person.getProfileImage();

        String imageName = new StringBuilder(person.getUsers().getFirstName()).append("-").append(person.getUsers().getLastName())
                .append("-").append(person.getId()).append("-profile").toString();
        Image profileImage = imageService.uploadImage(image, imageName);
        person.setProfileImage(profileImage);
        personService.save(person);

        Path filepath = Paths.get(profileImageDir, imageName.concat(".png"));
        File file = filepath.toFile();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Uploading image was not successful!");
            return "redirect:/personProfile";
        }

        if (oldProfileImage != null) {
            imageService.delete(oldProfileImage);
        }
        //</editor-fold>

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/removeProfileImage")
    private String removeProfileImage(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                      RedirectAttributes redirectAttributes) {
        if (person.getProfileImage() != null) {
            Image personProfile = person.getProfileImage();
            person.setProfileImage(null);
            personService.save(person);
            imageService.delete(personProfile);
        }

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/uploadBackgroundImage")
    private String uploadBackgroundImage(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                         RedirectAttributes redirectAttributes) {
        //<editor-fold desc="Save image in database(Image and Person table) and backgrounds image directory">
        Image oldBackgroundImage = person.getBackgroundImage();

        String imageName = new StringBuilder(person.getUsers().getFirstName()).append("-").append(person.getUsers().getLastName())
                .append("-").append(person.getId()).append("-background").toString();
        Image backgroundImage = imageService.uploadImage(image, imageName);
        person.setBackgroundImage(backgroundImage);
        personService.save(person);

        Path filepath = Paths.get(backgroundImageDir, imageName.concat(".png"));
        File file = filepath.toFile();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Uploading image was not successful!");
            return "redirect:/personProfile";
        }

        if (oldBackgroundImage != null) {
            imageService.delete(oldBackgroundImage);
        }
        //</editor-fold>

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/removeBackgroundImage")
    private String removeBackgroundImage(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                         RedirectAttributes redirectAttributes) {
        if (person.getBackgroundImage() != null) {
            Image personBackground = person.getBackgroundImage();
            person.setBackgroundImage(null);
            personService.save(person);
            imageService.delete(personBackground);
        }

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }
    //</editor-fold>
    //<editor-fold desc="Experience">
    @PostMapping("/addExperience")
    private String addExperience(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                                 @ModelAttribute("experience") Experience experience, RedirectAttributes redirectAttributes) {
        experience.setPerson(person);
        experienceService.save(experience);

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/editExperience")
    public String editExperience(@ModelAttribute("person") Person person, @ModelAttribute("expId") String id, Model model,
                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("person", person);
        model.addAttribute("experience", experienceService.findById(Integer.parseInt(id)));
        redirectAttributes.addFlashAttribute("experience", experienceService.findById(Integer.parseInt(id)));
        return "editExperience";
    }

    @PostMapping("/updateExperience")
    private String updateExperience(@ModelAttribute("person") Person person, @ModelAttribute("experience") Experience experience,
                                    @ModelAttribute("extId") Integer id, RedirectAttributes redirectAttributes) {
        experience.setPerson(person);
        experience.setId(id);
        experienceService.save(experience);

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/removeExperience")
    public String removeExperience(@ModelAttribute("expId") String id) {
        experienceService.delete(experienceService.findById(Integer.parseInt(id)));
        return "redirect:/personProfile";
    }
    //</editor-fold>
    //<editor-fold desc="Education">
    @PostMapping("/addEducation")
    private String addEducation(@ModelAttribute("person") Person person, @ModelAttribute("education") Education education,
                                 RedirectAttributes redirectAttributes) {
        education.setPerson(person);
        educationService.save(education);

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/editEducation")
    public String editEducation(@ModelAttribute("person") Person person, @ModelAttribute("eduId") String id, Model model,
                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("person", person);
        model.addAttribute("education", educationService.findById(Integer.parseInt(id)));
        redirectAttributes.addFlashAttribute("education", educationService.findById(Integer.parseInt(id)));
        return "editEducation";
    }

    @PostMapping("/updateEducation")
    private String updateEducation(@ModelAttribute("person") Person person, @ModelAttribute("education") Education education,
                                    @ModelAttribute("eduId") Integer id, RedirectAttributes redirectAttributes) {
        education.setPerson(person);
        education.setId(id);
        educationService.save(education);

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/removeEducation")
    public String removeEducation(@ModelAttribute("eduId") String id) {
        educationService.delete(educationService.findById(Integer.parseInt(id)));
        return "redirect:/personProfile";
    }
    //</editor-fold>
    //<editor-fold desc="Education">
    @PostMapping("/addSkill")
    private String addSkill(@ModelAttribute("person") Person person, @ModelAttribute("image") MultipartFile image,
                            @ModelAttribute("skill") Skill skill, RedirectAttributes redirectAttributes) {

        if (person.getSkills() != null && !person.getSkills().isEmpty() &&
                commonUtils.splitStringOfIntegers(
                        person.getSkills(), ", ").contains(skillService.findByName(skill.getName()).getId())) {
            redirectAttributes.addFlashAttribute("error", "This skill is already on your profile!");
        }
        else {
            person.setSkills(commonUtils.addItemToStringByCommaSeparator(person.getSkills(), skillService.findByName(skill.getName()).getId().toString()));
            personService.save(person);
        }

        redirectAttributes.addFlashAttribute("person", person);
        redirectAttributes.addFlashAttribute("experience", new Experience());
        redirectAttributes.addFlashAttribute("education", new Education());
        redirectAttributes.addFlashAttribute("skill", new Skill());

        redirectAttributes.addFlashAttribute("experienceList", experienceService.findExperiencesByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("educationList", educationService.findEducationsByPersonId(person.getId()));
        redirectAttributes.addFlashAttribute("skillObjList", skillService.findSkillsByPersonId(
                commonUtils.splitStringOfIntegers(person.getSkills(), ", ")));
        return "redirect:/personProfile";
    }

    @PostMapping("/removeSkill")
    public String removeSkill(@ModelAttribute("person") Person person, @ModelAttribute("skillId") String id) {
        person.setSkills(commonUtils.removeItemFromStringSeparatedByComma(person.getSkills(), id));
        personService.save(person);
        return "redirect:/personProfile";
    }
    //</editor-fold>
}
