package com.stepan.pet_project.web_student_progress.dao;

import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface StudentsGroupRepository extends JpaRepository<StudentsGroup, Long> {

    //Methods for StudentsGroup
    @Transactional(readOnly = true)
    @Query("select sg from StudentsGroup as sg " +
            "where sg.groupNumber like %:groupNumber% " +
            "and sg.faculty.facultyName like %:facultyName% " +
            "and sg.yearOfCreation between :yearFrom and :yearTo")
    List<StudentsGroup> getGroupsForAttributesBySort(String groupNumber, String facultyName, Calendar yearFrom, Calendar yearTo, Sort sort);

    @Transactional
    @Query("select min(sg.yearOfCreation) from StudentsGroup sg")
    Calendar getMinYearOfCreation();

    @Transactional
    @Query("select max(sg.yearOfCreation) from StudentsGroup sg")
    Calendar getMaxYearOfCreation();

    @Transactional
    @Query("select sg from StudentsGroup as sg where sg.groupNumber like %:groupNumber%")
    List<StudentsGroup> getStudentsGroupsByNumber(String groupNumber);


    //Methods for Faculty
    List<StudentsGroup> findStudentsGroupByFaculty_Id(long id);

    @Transactional(readOnly = true)
    @Query("select sg from StudentsGroup as sg " +
            "where sg.groupNumber like %:groupNumber% " +
            "and sg.faculty.facultyName like %:facultyName% " +
            "and sg.faculty.id =:facultyId " +
            "and sg.yearOfCreation between :yearFrom and :yearTo")
    List<StudentsGroup> getGroupsForAttributesAndFacultyIdBySort(String groupNumber, String facultyName, Calendar yearFrom, Calendar yearTo, long facultyId, Sort sort);

}
