package com.stepan.pet_project.web_student_progress.service.student_service;

import com.stepan.pet_project.web_student_progress.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface StudentService {
    List<Student> getAllStudent();

    void deleteStudentById(long id);

    void saveOrUpdateStudent(Student student);

    List<Student> getStudentsByStudentsGroupId(long id);

    List<Student> getStudentsByFacultyId(long id);

    Student getStudentById(long id);

    List<Student> getStudentByFullName(String fullName);

    List<Student> getStudentsByAttributesBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom, Calendar dateOfBirthTo,
            String city, float scholarshipFrom, float scholarshipTo,
            String sortBy);

    List<Student> getStudentsByAttrAndGroupIdBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long studentGroupId, String sortBy);

    List<Student> getStudentsByAttrAndFacultyIdBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long facultyId, String sortBy);

}
