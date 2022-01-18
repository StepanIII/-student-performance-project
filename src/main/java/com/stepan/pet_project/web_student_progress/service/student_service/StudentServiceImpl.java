package com.stepan.pet_project.web_student_progress.service.student_service;

import com.stepan.pet_project.web_student_progress.dao.StudentRepository;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import com.stepan.pet_project.web_student_progress.service.students_group_service.StudentsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentsGroupService studentsGroupService;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        Student student;
        Optional<Student> optionalStudent = studentRepository.findById(id);

        student = optionalStudent.get();
        return student;
    }

    @Override
    public List<Student> getStudentByFullName(String fullName) {
        return studentRepository.getStudentByFullName(fullName);
    }

    @Override
    public List<Student> getStudentsByAttributesBySort(long studentNumber, String fullName, short examPointsFrom, short examPointsTo, String studentGroupName, Calendar dateOfBirthFrom, Calendar dateOfBirthTo, String city, float scholarshipFrom, float scholarshipTo, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        if (studentNumber == 0) {
            return studentRepository.getStudentsByAttributesBySort(fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, sort);
        }
        return studentRepository.getStudentsByAttributesBySort(studentNumber, fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, sort);
    }

//    @Override
//    public Short getMaxExamPoint() {
//        return studentRepository.getMaxExamPoint();
//    }
//
//    @Override
//    public Float getMaxScholarship() {
//        return studentRepository.getMaxScholarship();
//    }
//
//    @Override
//    public String getMaxDateOfBirth() {
//        Calendar birth = studentRepository.getMaxDateOfBirth();
//        String year = String.valueOf(birth.get(Calendar.YEAR));
//        String month = String.valueOf(birth.get(Calendar.MONTH) + 1);
//        String date = String.valueOf(birth.get(Calendar.DATE));
//
//        if(month.length()<2) month = 0 + month;
//        if(date.length()<2) date = 0 + date;
//
//        return String.format("%s-%s-%s", year, month, date);
//    }
//
//    @Override
//    public String getMinDateOfBirth() {
//        Calendar birth = studentRepository.getMinDateOfBirth();
//        String year = String.valueOf(birth.get(Calendar.YEAR));
//        String month = String.valueOf(birth.get(Calendar.MONTH) + 1);
//        String date = String.valueOf(birth.get(Calendar.DATE));
//
//        if(month.length()<2) month = 0 + month;
//        if(date.length()<2) date = 0 + date;
//
//        return String.format("%s-%s-%s", year, month, date);
//    }

    @Override
    public List<Student> getStudentsByAttrAndGroupIdBySort(long studentNumber, String fullName, short examPointsFrom, short examPointsTo, String studentGroupName, Calendar dateOfBirthFrom, Calendar dateOfBirthTo, String city, float scholarshipFrom, float scholarshipTo, long studentGroupId, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        if(studentNumber == 0) {
            return studentRepository.getStudentsByAttrAndGroupIdBySort(fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, studentGroupId, sort);
        }

        return studentRepository.getStudentsByAttrAndGroupIdBySort(studentNumber, fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, studentGroupId, sort);
    }

    @Override
    public List<Student> getStudentsByAttrAndFacultyIdBySort(long studentNumber, String fullName, short examPointsFrom, short examPointsTo, String studentGroupName, Calendar dateOfBirthFrom, Calendar dateOfBirthTo, String city, float scholarshipFrom, float scholarshipTo, long facultyId, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        if(studentNumber == 0) {
            return studentRepository.getStudentsByAttrAndFacultyIdBySort(fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, facultyId, sort);
        }

        return studentRepository.getStudentsByAttrAndFacultyIdBySort(studentNumber, fullName, examPointsFrom, examPointsTo, studentGroupName, dateOfBirthFrom, dateOfBirthTo, city, scholarshipFrom, scholarshipTo, facultyId, sort);
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByStudentsGroupId(long id) {
        return studentRepository.findStudentsByStudentsGroup_Id(id);
    }

    @Override
    public List<Student> getStudentsByFacultyId(long id) {
        List<Student> students = new ArrayList<>();
        List<StudentsGroup> studentsGroups = studentsGroupService.getGroupsByFacultyId(id);

        for (StudentsGroup group : studentsGroups) {
            students.addAll(group.getListStudents());
        }
        return students;
    }
}
