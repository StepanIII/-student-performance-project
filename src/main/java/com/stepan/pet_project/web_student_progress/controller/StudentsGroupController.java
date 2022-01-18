package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.Faculty;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import com.stepan.pet_project.web_student_progress.select_attribute.AttributeStudentsGroup;
import com.stepan.pet_project.web_student_progress.service.appraisal_service.AppraisalService;
import com.stepan.pet_project.web_student_progress.service.facultie_service.FacultyService;
import com.stepan.pet_project.web_student_progress.service.student_service.StudentService;
import com.stepan.pet_project.web_student_progress.service.students_group_service.StudentsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/students_group")
public class StudentsGroupController {

    @Autowired
    private StudentsGroupService studentsGroupService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private AttributeStudentsGroup attributeStudentsGroup;


    @RequestMapping("/all")
    public String shawStudentsGroup(Model model) {
        attributeStudentsGroup.resetAttributes();
        List<StudentsGroup> allStudentsGroup = studentsGroupService.getAllGroups();

        model.addAttribute("allStudentsGroup", allStudentsGroup);
        model.addAttribute("attributes", attributeStudentsGroup);
        return "group-all";
    }

    @RequestMapping("/faculty_groups")
    public String facultyGroups(@RequestParam(name = "id") long id, Model model) {
        attributeStudentsGroup.resetAttributes();
        List<StudentsGroup> studentsGroups = facultyService.getStudentsGroupsByFacultyId(id);
        String facultyName = facultyService.getFacultyById(id).getFacultyName();

        attributeStudentsGroup.setFacultyId(id);
        attributeStudentsGroup.setFacultyName(facultyName);

        model.addAttribute("allStudentsGroup", studentsGroups);
        model.addAttribute("attributes", attributeStudentsGroup);
        return "faculty-groups";
    }


    @RequestMapping("/add")
    public String addNewStudentsGroup(@RequestParam("facultyId") long facultyId, Model model) {
        StudentsGroup studentsGroup = new StudentsGroup(new Faculty());
        List<Faculty> allFaculty = facultyService.getAllFaculty();
        String view = "group-add";


        if(facultyId != 0){
            Faculty faculty = facultyService.getFacultyById(facultyId);

            studentsGroup.setFaculty(faculty);
            studentsGroup.setFacultyId(facultyId);
            studentsGroup.setFromFaculty(true);

            view = "faculty-group-add";
        }

        model.addAttribute("studentsGroup", studentsGroup);
        model.addAttribute("allFaculty", allFaculty);
        return view;
    }


    @RequestMapping("/save")
    public String saveStudentsGroup(@Valid @ModelAttribute("studentsGroup") StudentsGroup studentsGroup,
                                    BindingResult bindingResult,
                                    Model model) {

        if(bindingResult.hasErrors()) {
            List<Faculty> allFaculty = facultyService.getAllFaculty();
            studentsGroup.setFaculty(allFaculty.get(0));
            String view = "group-add";

            if(studentsGroup.isFromFaculty()) {
                long facultyId = studentsGroup.getFacultyId();

                studentsGroup.setFaculty(facultyService.getFacultyById(facultyId));
                view = "faculty-group-add";
            }

            model.addAttribute("allFaculty", allFaculty);
            model.addAttribute("studentsGroup", studentsGroup);

            return view;
        }

        long facultyId = studentsGroup.getFacultyId();

        Faculty faculty = facultyService.getFacultyById(facultyId);

        studentsGroup.setFaculty(faculty);
        studentsGroupService.saveOrUpdateGroup(studentsGroup);

        if(studentsGroup.isFromFaculty()) return "redirect:/students_group/select?facultyId=" + facultyId;

        return "redirect:/students_group/select?facultyId=0";
    }

    @RequestMapping("/delete")
    public String deleteStudentsGroup(@RequestParam(name = "id") long id,
                                      @RequestParam(name = "isFromFaculty") boolean isFromFaculty) {
        List<Student> students = studentService.getStudentsByStudentsGroupId(id);
        long facultyId = studentsGroupService.getGroupById(id).getFaculty().getId();

        //-------------------------------------------------------------------------------------
        for (Student student : students) {
            appraisalService.deleteAppraisalsByStudentNumber(student.getStudentNumber());
        }
        //------------------------------------------------------------------------------------

        studentsGroupService.deleteGroupById(id);

        if(isFromFaculty) return "redirect:/students_group/select?facultyId=" + facultyId;
        return "redirect:/students_group/select?facultyId=0";
    }

    @RequestMapping("/update")
    public String updateStudentsGroup(@RequestParam(name = "id") long id,
                                      @RequestParam(name = "isFromFaculty") boolean isFromFaculty,
                                      Model model) {

        StudentsGroup studentsGroup  = studentsGroupService.getGroupById(id);
        List<Faculty> allFaculty = facultyService.getAllFaculty();

        studentsGroup.setFromFaculty(isFromFaculty);
        studentsGroup.setFromUpdate(true);

        model.addAttribute("studentsGroup", studentsGroup);
        model.addAttribute("allFaculty", allFaculty);
        return "group-add";
    }

    @RequestMapping("/select")
    public String selectStudentsGroup(@ModelAttribute("attributes") AttributeStudentsGroup attribute,
                                          @RequestParam("facultyId") long facultyId,
                                          Model model) {
        List<StudentsGroup> studentsGroups;

        if (attribute.isSelected()) {
            attribute = attributeStudentsGroup;
        } else {
            attributeStudentsGroup = attribute;
        }


        String groupNumber = attribute.getGroupNumber();
        String facultyName = attribute.getFacultyName();

        Calendar yearFrom = attribute.getCalendarFrom();
        Calendar yearTo = attribute.getCalendarTo();

        String sort = attribute.getSort();

        studentsGroups = studentsGroupService.getGroupsForAttributesBySort(groupNumber, facultyName, yearFrom, yearTo, facultyId, sort);

        model.addAttribute("allStudentsGroup", studentsGroups);
        model.addAttribute("attributes", attribute);

        if (facultyId != 0) return "faculty-groups";
        return "group-all";
    }
}
