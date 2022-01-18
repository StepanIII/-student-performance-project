package com.stepan.pet_project.web_student_progress.dao;

import com.stepan.pet_project.web_student_progress.entity.Faculty;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Transactional(readOnly = true)
    @Query("select faculty from Faculty as faculty " +
            "where faculty.facultyName like %:facultyName% " +
            "and faculty.dean like %:deanFullName%")
    List<Faculty> getFacultiesByAttributesBySort(String facultyName, String deanFullName, Sort sort);

    @Transactional
    @Query("select students from Student as students where students.studentsGroup.faculty.id =:id")
    List<Student> getStudentsByFaculty(long id);

    @Transactional
    @Query("select sg from StudentsGroup as sg where sg.faculty.id = :id")
    List<StudentsGroup> getStudentsGroupsByFacultyId(long id);
}
