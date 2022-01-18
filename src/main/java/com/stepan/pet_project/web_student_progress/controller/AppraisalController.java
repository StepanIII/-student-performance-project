package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.Appraisal;
import com.stepan.pet_project.web_student_progress.entity.Discipline;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.select_attribute.AttributeAppraisal;
import com.stepan.pet_project.web_student_progress.service.appraisal_service.AppraisalService;
import com.stepan.pet_project.web_student_progress.service.discipline_service.DisciplineService;
import com.stepan.pet_project.web_student_progress.service.student_service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/appraisal")
public class AppraisalController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private AttributeAppraisal attributeAppraisal;

    @GetMapping("/all")
    public String shawAppraisals(Model model) {
        attributeAppraisal.resetAttributes();
        List<Appraisal> allAppraisal = appraisalService.getAllAppraisal();

        model.addAttribute("attributes", attributeAppraisal);
        model.addAttribute("allAppraisal", allAppraisal);
        return "appraisal-all";
    }

    @GetMapping("/discipline_appraisal")
    public String shawDisciplineAppraisals(@RequestParam(name = "id") long id, Model model) {
        attributeAppraisal.resetAttributes();
        List<Appraisal> appraisals = appraisalService.getAppraisalByDisciplineId(id);
        String disciplineName = disciplineService.getDisciplineById(id).getDisciplineName();

        attributeAppraisal.setDiscipline(disciplineName);
        attributeAppraisal.setDisciplineId(id);

        model.addAttribute("allAppraisal", appraisals);
        model.addAttribute("attributes", attributeAppraisal);
        return "discipline-appraisal";
    }

    @GetMapping("/student_appraisal")
    public String shawStudentAppraisals(@RequestParam(name = "id") long id, Model model) {
        attributeAppraisal.resetAttributes();
        List<Appraisal> appraisals = appraisalService.getAppraisalByStudentNumber(id);
        String studentName = studentService.getStudentById(id).getFullName();

        attributeAppraisal.setStudentFullName(studentName);
        attributeAppraisal.setStudentNumber(id);

        model.addAttribute("allAppraisal", appraisals);
        model.addAttribute("attributes", attributeAppraisal);
        return "student-appraisal";
    }

    @RequestMapping("/add")
    public String addNewAppraisal(@RequestParam("disciplineId") long disciplineId,
                                  @RequestParam("studentNumber") long studentNumber,
                                  @ModelAttribute("attribute") AttributeAppraisal attribute,
                                  Model model) {

        Appraisal appraisal = new Appraisal(new Student(), new Discipline());
        List<Student> allStudents = studentService.getAllStudent();
        List<Discipline> allDisciplines = disciplineService.getAllDiscipline();
        String view = "appraisal-add";

        if (attribute.getStudentFullName() != null) {
            allStudents = studentService.getStudentByFullName(attribute.getStudentFullName());
        }

        if(disciplineId != 0) {
            Discipline discipline = disciplineService.getDisciplineById(disciplineId);
            appraisal = new Appraisal(new Student(), discipline);
            appraisal.setDisciplineId(disciplineId);
            view = "discipline-appraisal-add";
        } else if(studentNumber != 0) {
            Student student = studentService.getStudentById(studentNumber);
            appraisal = new Appraisal(student, new Discipline());
            appraisal.setStudentNumber(studentNumber);
            view = "student-appraisal-add";
        }

        model.addAttribute("appraisal", appraisal);
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("allDisciplines", allDisciplines);
        model.addAttribute("attribute", attributeAppraisal);

        return view;
    }

    @RequestMapping("/save")
    public String saveAppraisal(@Valid @ModelAttribute("appraisal") Appraisal appraisal,
                                BindingResult bindingResult,
                                Model model) {

        if(bindingResult.hasErrors()) {
            List<Student> allStudents = studentService.getAllStudent();
            List<Discipline> allDisciplines = disciplineService.getAllDiscipline();
            String view = "appraisal-add";

            appraisal.setStudent(allStudents.get(0));
            appraisal.setDiscipline(allDisciplines.get(0));

            if (appraisal.getDisciplineId() != 0) {
                Discipline discipline = disciplineService.getDisciplineById(appraisal.getDisciplineId());
                appraisal.setDiscipline(discipline);
                view = "discipline-appraisal-add";
            } else if(appraisal.getStudentNumber() != 0) {
                Student student = studentService.getStudentById(appraisal.getStudentNumber());
                appraisal.setStudent(student);
                view = "student-appraisal-add";
            }

            model.addAttribute("appraisal", appraisal);
            model.addAttribute("allStudents", allStudents);
            model.addAttribute("allDisciplines", allDisciplines);
            model.addAttribute("attribute", attributeAppraisal);

            return view;
        }

        Student student = studentService.getStudentById(appraisal.getStudentNumber());
        Discipline discipline = disciplineService.getDisciplineById(appraisal.getDisciplineId());

        appraisal.setStudent(student);
        appraisal.setDiscipline(discipline);
        appraisalService.saveOrUpdateAppraisal(appraisal);

        return "redirect:/appraisal/select";
    }

    @GetMapping("/update")
    public String updateAppraisal(@RequestParam(name = "id") long id, Model model) {
        Appraisal appraisal = appraisalService.getAppraisalById(id);
        List<Student> allStudents = studentService.getAllStudent();
        List<Discipline> allDisciplines = disciplineService.getAllDiscipline();
        appraisal.setFromUpdate(true);

        model.addAttribute("appraisal", appraisal);
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("allDisciplines", allDisciplines);
        model.addAttribute("attribute", attributeAppraisal);
        return "appraisal-add";
    }

    @GetMapping("/delete")
    public String deleteAppraisal(@RequestParam(name = "id") long id) {
        appraisalService.deleteAppraisalById(id);
        return "redirect:/appraisal/select";
    }

    @GetMapping("/select")
    public String selectAppraisals(@ModelAttribute("attributes") AttributeAppraisal attribute, Model model) {
        List<Appraisal> allAppraisal;

        if (attribute.isSelected()) {
            attribute = attributeAppraisal;
        } else {
            attributeAppraisal = attribute;
        }

        long studentNumber = attribute.getStudentNumber();
        String studentFullName = attribute.getStudentFullName();
        String discipline = attribute.getDiscipline();

        short scoreFrom = attribute.getScoreFrom();
        short scoreTo = attribute.getScoreTo();

        Calendar dateAddedFrom = attribute.getCalDateAddedFrom();
        Calendar dateAddedTo = attribute.getCalDateAddedTo();

        String sort = attribute.getSort();

        long disciplineId = attribute.getDisciplineId();

        String view;

        if(disciplineId != 0) {
            allAppraisal = appraisalService.getAppraisalByAttrAndDisciplineIdBySort(studentNumber, studentFullName,
                                                                                    discipline, scoreFrom, scoreTo,
                                                                                    dateAddedFrom, dateAddedTo,
                                                                                    disciplineId, sort);
            view = "discipline-appraisal";
        } else if(studentNumber != 0) {
            allAppraisal = appraisalService.getAppraisalByAttributesBySort(studentNumber, studentFullName, discipline,
                                                                            scoreFrom, scoreTo, dateAddedFrom, dateAddedTo, sort);
            view = "student-appraisal";
        } else {
            allAppraisal = appraisalService.getAppraisalByAttributesBySort(studentNumber, studentFullName, discipline,
                                                                            scoreFrom, scoreTo, dateAddedFrom, dateAddedTo, sort);
            view ="appraisal-all";
        }

        model.addAttribute("allAppraisal", allAppraisal);
        model.addAttribute("attributes", attribute);

        return view;
    }
}
