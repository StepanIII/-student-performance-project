package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.Discipline;
import com.stepan.pet_project.web_student_progress.select_attribute.AttributeDiscipline;
import com.stepan.pet_project.web_student_progress.service.appraisal_service.AppraisalService;
import com.stepan.pet_project.web_student_progress.service.discipline_service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private AttributeDiscipline attributeDiscipline;

    @RequestMapping("/all")
    public String shawDisciplines(Model model) {
        attributeDiscipline.resetAttributes();
        List<Discipline> allDiscipline = disciplineService.getAllDiscipline();

        model.addAttribute("allDiscipline", allDiscipline);
        model.addAttribute("attributes", attributeDiscipline);
        return "discipline-all";
    }

    @RequestMapping("/add")
    public String addNewDiscipline(Model model) {
        Discipline discipline = new Discipline();
        model.addAttribute("discipline", discipline);
        return "discipline-add";
    }

    @RequestMapping("/save")
    public String saveDiscipline(@Valid @ModelAttribute("discipline") Discipline discipline, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "discipline-add";
        disciplineService.saveOrUpdateDiscipline(discipline);
        return "redirect:/discipline/select";
    }

    @RequestMapping("/delete")
    public String deleteDiscipline(@RequestParam(name = "id") long id) {
        appraisalService.deleteAppraisalsByDiscipline_Id(id);
        disciplineService.deleteDisciplineById(id);
        return "redirect:/discipline/select";
    }

    @RequestMapping("/update")
    public String updateDiscipline(@RequestParam(name = "id") long id, Model model) {
        Discipline discipline = disciplineService.getDisciplineById(id);
        discipline.setFromUpdate(true);

        model.addAttribute("discipline", discipline);
        return "discipline-add";
    }

    @RequestMapping("/select")
    public String selectDisciplines(@ModelAttribute("attributes") AttributeDiscipline attribute,
                                            Model model) {
        if (attribute.isSelected()) {
            attribute = attributeDiscipline;
        } else {
            attributeDiscipline = attribute;
        }

        List<Discipline> allDiscipline;

        String disciplineName = attribute.getDisciplineName();
        int hourFrom = attribute.getHourFrom();
        int hourTo = attribute.getHourTo();
        String sort = attribute.getSort();

        allDiscipline = disciplineService.getDisciplineByAttributesBySort(disciplineName, hourFrom, hourTo, sort);

        model.addAttribute("allDiscipline", allDiscipline);
        model.addAttribute("attributes", attribute);

        return "discipline-all";
    }
}
