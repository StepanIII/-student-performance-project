package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.Faculty;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.select_attribute.AttributeFaculty;
import com.stepan.pet_project.web_student_progress.service.appraisal_service.AppraisalService;
import com.stepan.pet_project.web_student_progress.service.facultie_service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private AttributeFaculty attributeFaculty;

    @RequestMapping("/all")
    public String shawFaculties(Model model) {
        attributeFaculty.resetAttributes();
        List<Faculty> allFaculty = facultyService.getAllFaculty();

        model.addAttribute("allFaculty", allFaculty);
        model.addAttribute("attributes", attributeFaculty);
        return "faculty-all";
    }

    @RequestMapping("/add")
    public String addNewFaculty(Model model) {
        Faculty faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        return "faculty-add";
    }

    @RequestMapping("/save")
    public String saveFaculty(@Valid @ModelAttribute("faculty") Faculty faculty, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "faculty-add";
        facultyService.saveOrUpdateFaculty(faculty);
        return "redirect:/faculty/select";
    }

    @RequestMapping("/update")
    public String updateFaculty(@RequestParam(name = "id") long id, Model model) {
        Faculty faculty  = facultyService.getFacultyById(id);
        faculty.setFromUpdate(true);

        model.addAttribute("faculty", faculty);
        return "faculty-add";
    }

    @RequestMapping("/delete")
    public String deleteFaculty(@RequestParam(name = "id") long id) {
        List<Student> students = facultyService.getStudentsByFaculty(id);

        for (Student student : students) appraisalService.deleteAppraisalsByStudentNumber(student.getStudentNumber());

        facultyService.deleteFacultyById(id);
        return "redirect:/faculty/select";
    }


    @RequestMapping("/select")
    public String selectFaculties(@ModelAttribute("attributes") AttributeFaculty attribute,
                                            Model model) {
        if (attribute.isSelected()) {
            attribute = attributeFaculty;
        } else {
            attributeFaculty = attribute;
        }

        List<Faculty> allFaculty;

        String facultyName = attribute.getFacultyName();
        String deanFullName = attribute.getDeanFullName();
        String sort = attribute.getSort();

        allFaculty = facultyService.getFacultiesByAttributesBySort(facultyName, deanFullName, sort);

        model.addAttribute("allFaculty", allFaculty);
        model.addAttribute("attributes", attribute);

        return "faculty-all";
    }

}
