package com.stepan.pet_project.web_student_progress.service.facultie_service;

import com.stepan.pet_project.web_student_progress.entity.Faculty;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAllFaculty();

    Faculty getFacultyById(long id);

    void deleteFacultyById(long id);

    void saveOrUpdateFaculty(Faculty faculty);

    List<Faculty> getFacultiesByAttributesBySort(String facultyName, String deanFullName, String sortBy);

    List<Student> getStudentsByFaculty(long id);

    List<StudentsGroup> getStudentsGroupsByFacultyId(long id);

}
