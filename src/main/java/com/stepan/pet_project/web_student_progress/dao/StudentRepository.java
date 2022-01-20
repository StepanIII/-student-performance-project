package com.stepan.pet_project.web_student_progress.dao;

import com.stepan.pet_project.web_student_progress.entity.Student;
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

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.studentNumber =:studentNumber " +
            "and student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo")
    List<Student> getStudentsByAttributesBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city, float scholarshipFrom, float scholarshipTo,
            Sort sort);

    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo")
    List<Student> getStudentsByAttributesBySort(
            String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            Sort sort);

    //Methods for StudentsGroup
    List<Student> findStudentsByStudentsGroup_Id(long id);

    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.studentNumber =:studentNumber " +
            "and student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo " +
            "and student.studentsGroup.id =:studentGroupId")
    List<Student> getStudentsByAttrAndGroupIdBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long studentGroupId, Sort sort);

    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo " +
            "and student.studentsGroup.id =:studentGroupId")
    List<Student> getStudentsByAttrAndGroupIdBySort(
            String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long studentGroupId, Sort sort);

    @Transactional
    @Query("select student from Student as student where student.fullName like %:fullName%")
    List<Student> getStudentByFullName(String fullName);

    //Methods for Faculty
    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.studentNumber =:studentNumber " +
            "and student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo " +
            "and student.studentsGroup.faculty.id =:facultyId" )
    List<Student> getStudentsByAttrAndFacultyIdBySort(
            long studentNumber, String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long facultyId, Sort sort);

    @Transactional(readOnly = true)
    @Query("select student from Student as student " +
            "where student.fullName like %:fullName% " +
            "and student.examPoints between :examPointsFrom and :examPointsTo " +
            "and student.studentsGroup.groupNumber like %:studentGroupName% " +
            "and student.dateOfBirth between :dateOfBirthFrom and :dateOfBirthTo " +
            "and student.city like %:city% " +
            "and student.scholarship between :scholarshipFrom and :scholarshipTo " +
            "and student.studentsGroup.faculty.id =:facultyId")
    List<Student> getStudentsByAttrAndFacultyIdBySort(
            String fullName,
            short examPointsFrom, short examPointsTo,
            String studentGroupName,
            Calendar dateOfBirthFrom,Calendar dateOfBirthTo,
            String city,
            float scholarshipFrom, float scholarshipTo,
            long facultyId, Sort sort);

}
