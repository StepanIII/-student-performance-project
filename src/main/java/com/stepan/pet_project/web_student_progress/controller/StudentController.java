package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import com.stepan.pet_project.web_student_progress.select_attribute.AttributeStudent;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentsGroupService studentsGroupService;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private AttributeStudent attributeStudent;

    @RequestMapping("/all")
    public String shawStudents(Model model) {
        attributeStudent.resetAttributes();
        List<Student> allStudent = studentService.getAllStudent();

        model.addAttribute("allStudent", allStudent);
        model.addAttribute("attributes", attributeStudent);
        return "student-all";
    }

    @RequestMapping("/group_students")
    public String groupStudents(@RequestParam(name = "id") long id, Model model) {
        attributeStudent.resetAttributes();
        List<Student> students = studentService.getStudentsByStudentsGroupId(id);
        String groupName = studentsGroupService.getGroupById(id).getGroupNumber();

        attributeStudent.setGroupId(id);
        attributeStudent.setStudentGroupName(groupName);

        model.addAttribute("attributes", attributeStudent);
        model.addAttribute("allStudent", students);
        return "group-students";
    }

    @RequestMapping("/faculty_students")
    public String facultyStudents(@RequestParam(name = "id") long id, Model model) {
        attributeStudent.resetAttributes();
        List<Student> students = studentService.getStudentsByFacultyId(id);
        String facultyName = facultyService.getFacultyById(id).getFacultyName();

        attributeStudent.setFacultyName(facultyName);
        attributeStudent.setFacultyId(id);

        model.addAttribute("attributes", attributeStudent);
        model.addAttribute("allStudent",students);
        return "faculty-students";
    }

    @RequestMapping("/add")
    public String addNewStudent(@RequestParam("facultyId") long facultyId,
                                @RequestParam("groupId") long groupId,
                                @ModelAttribute("attribute") AttributeStudent attribute,
                                Model model) {
        List<StudentsGroup> allStudentsGroups = null;
        String view = "";
        Student student = new Student(new StudentsGroup());

        if (attribute.getStudentGroupName() != null) {
            allStudentsGroups = studentsGroupService.getStudentsGroupsByNumber(attribute.getStudentGroupName());
        }

        if(facultyId != 0) {
            String facultyName = facultyService.getFacultyById(facultyId).getFacultyName();
            allStudentsGroups = studentsGroupService.getGroupsByFacultyId(facultyId);
            student.setFacultyName(facultyName);
            student.setFacultyId(facultyId);
            student.setFromFaculty(true);
            view = "faculty-student-add";
        }else if (groupId != 0) {
            StudentsGroup group = studentsGroupService.getGroupById(groupId);
            student.setStudentsGroup(group);
            student.setGroupId(groupId);
            student.setFromGroup(true);
            view ="group-student-add";
        }else {
            view = "student-add";
        }

        model.addAttribute("student", student);
        model.addAttribute("allStudentsGroups", allStudentsGroups);
        model.addAttribute("attribute", attributeStudent);

        return view;
    }

    @RequestMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult,
                              Model model) {

        if(bindingResult.hasErrors()) {
            List<StudentsGroup> allStudentsGroups = studentsGroupService.getAllGroups();
            student.setStudentsGroup(allStudentsGroups.get(0));
            String view = "student-add";

            if (student.isFromFaculty()) {
                allStudentsGroups = studentsGroupService.getGroupsByFacultyId(student.getFacultyId());
                student.setStudentsGroup(allStudentsGroups.get(0));
                view = "faculty-student-add";

            } else if (student.isFromGroup()) {
                StudentsGroup group = studentsGroupService.getGroupById(student.getGroupId());
                student.setStudentsGroup(group);
                view = "group-student-add";
            }

            model.addAttribute("student", student);
            model.addAttribute("allStudentsGroups", allStudentsGroups);
            model.addAttribute("attribute", attributeStudent);

            return view;
        }

        long groupId = student.getGroupId();
        long facultyId = student.getFacultyId();
        StudentsGroup studentsGroup = studentsGroupService.getGroupById(groupId);

        student.setStudentsGroup(studentsGroup);
        studentService.saveOrUpdateStudent(student);

        if (student.isFromFaculty()) return "redirect:/student/select?facultyId=" + facultyId + "&groupId=0";
        if (student.isFromGroup()) return "redirect:/student/select?facultyId=0&groupId=" + groupId;

        return "redirect:/student/select?facultyId=0&groupId=0";
    }

    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam(name = "id") long id,
                                @RequestParam(name = "isFromGroup") boolean isFromGroup,
                                @RequestParam(name = "isFromFaculty") boolean isFromFaculty) {

        long facultyId = studentService.getStudentById(id).getStudentsGroup().getFaculty().getId();
        long groupId = studentService.getStudentById(id).getStudentsGroup().getId();

        appraisalService.deleteAppraisalsByStudentNumber(id);
        studentService.deleteStudentById(id);

        if (isFromFaculty) return "redirect:/student/select?facultyId=" + facultyId + "&groupId=0";
        if (isFromGroup) return "redirect:/student/select?facultyId=0&groupId=" + groupId;

        return "redirect:/student/select?facultyId=0&groupId=0";
    }

    @RequestMapping("/update")
    public String updateStudent(@RequestParam(name = "id") long id,
                                @RequestParam(name = "isFromGroup") boolean isFromGroup,
                                @RequestParam(name = "isFromFaculty") boolean isFromFaculty,
                                Model model) {
        Student student = studentService.getStudentById(id);

        student.setFromGroup(isFromGroup);
        student.setFromFaculty(isFromFaculty);
        student.setFromUpdate(true);

        List<StudentsGroup> allStudentsGroups = studentsGroupService.getAllGroups();

        model.addAttribute("student", student);
        model.addAttribute("allStudentsGroups", allStudentsGroups);
        model.addAttribute("attribute", attributeStudent);
        return "student-add";
    }

    @RequestMapping("/select")
    public String selectStudents(@ModelAttribute("attributes") AttributeStudent attribute,
                                            @RequestParam("facultyId") long facultyId,
                                            @RequestParam("groupId") long groupId,
                                            Model model) {

        List<Student> allStudent;

        if (attribute.isSelected()) {
            attribute = attributeStudent;
        } else {
            attributeStudent = attribute;
        }

        long studentNumber = attribute.getStudentNumber();
        String fullName = attribute.getFullName();

        short examPointsFrom = attribute.getExamPointsFrom();
        short examPointsTo = attribute.getExamPointsTo();

        String studentGroupName = attribute.getStudentGroupName();

        Calendar dateOfBirthFrom = attribute.getCalendarFrom();
        Calendar dateOfBirthTo = attribute.getCalendarTo();

        String city = attribute.getCity();

        float scholarshipFrom = attribute.getScholarshipFrom();
        float scholarshipTo = attribute.getScholarshipTo();

        String sort = attribute.getSort();
        String view = "";

        if(facultyId != 0) {
            allStudent = studentService.getStudentsByAttrAndFacultyIdBySort(studentNumber, fullName, examPointsFrom,
                    examPointsTo, studentGroupName,
                    dateOfBirthFrom, dateOfBirthTo,
                    city, scholarshipFrom, scholarshipTo,
                    facultyId, sort);

            view = "faculty-students";

        } else if(groupId != 0) {
            allStudent = studentService.getStudentsByAttrAndGroupIdBySort(studentNumber, fullName,
                    examPointsFrom, examPointsTo,
                    studentGroupName, dateOfBirthFrom,
                    dateOfBirthTo, city, scholarshipFrom,
                    scholarshipTo, groupId, sort);

            view = "group-students";

        } else {
            allStudent = studentService.getStudentsByAttributesBySort(studentNumber, fullName,
                    examPointsFrom, examPointsTo,
                    studentGroupName, dateOfBirthFrom,
                    dateOfBirthTo, city, scholarshipFrom,
                    scholarshipTo, sort);

            view = "student-all";
        }

        model.addAttribute("attributes", attribute);
        model.addAttribute("allStudent", allStudent);
        return view;
    }
}
