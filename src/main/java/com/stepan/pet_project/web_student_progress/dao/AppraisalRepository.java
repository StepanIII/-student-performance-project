package com.stepan.pet_project.web_student_progress.dao;

import com.stepan.pet_project.web_student_progress.entity.Appraisal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long> {

    //Methods for appraisal
    @Transactional
    @Query("select appraisal from Appraisal as appraisal " +
            "where appraisal.student.studentNumber =:studentNumber " +
            "and appraisal.student.fullName like %:studentFullName% " +
            "and appraisal.discipline.disciplineName like %:discipline% " +
            "and appraisal.score between :scoreFrom and :scoreTo " +
            "and appraisal.dateAdded between :dateAddedFrom and :dateAddedTo")
    List<Appraisal> getAppraisalByAttributesBySort(long studentNumber, String studentFullName, String discipline,
                                                   short scoreFrom, short scoreTo,
                                                   Calendar dateAddedFrom, Calendar dateAddedTo,
                                                   Sort sort);

    @Transactional
    @Query("select appraisal from Appraisal as appraisal " +
            "where appraisal.student.fullName like %:studentFullName% " +
            "and appraisal.discipline.disciplineName like %:discipline% " +
            "and appraisal.score between :scoreFrom and :scoreTo " +
            "and appraisal.dateAdded between :dateAddedFrom and :dateAddedTo")
    List<Appraisal> getAppraisalByAttributesBySort(String studentFullName, String discipline,
                                                   short scoreFrom, short scoreTo,
                                                   Calendar dateAddedFrom, Calendar dateAddedTo,
                                                   Sort sort);

    //Methods for discipline
    List<Appraisal> findAppraisalByDiscipline_Id(long id);

    @Transactional
    @Query("select appraisal from Appraisal as appraisal " +
            "where appraisal.student.studentNumber =:studentNumber " +
            "and appraisal.student.fullName like %:studentFullName% " +
            "and appraisal.discipline.disciplineName like %:discipline% " +
            "and appraisal.score between :scoreFrom and :scoreTo " +
            "and appraisal.discipline.id =:disciplineId "+
            "and appraisal.dateAdded between :dateAddedFrom and :dateAddedTo")
    List<Appraisal> getAppraisalByAttrAndDisciplineIdBySort(long studentNumber, String studentFullName, String discipline,
                                                            short scoreFrom, short scoreTo,
                                                            Calendar dateAddedFrom, Calendar dateAddedTo,
                                                            long disciplineId, Sort sort);

    @Transactional
    @Query("select appraisal from Appraisal as appraisal " +
            "where  appraisal.student.fullName like %:studentFullName% " +
            "and appraisal.discipline.disciplineName like %:discipline% " +
            "and appraisal.score between :scoreFrom and :scoreTo " +
            "and appraisal.discipline.id =:disciplineId "+
            "and appraisal.dateAdded between :dateAddedFrom and :dateAddedTo")
    List<Appraisal> getAppraisalByAttrAndDisciplineIdBySort(String studentFullName, String discipline,
                                                             short scoreFrom, short scoreTo,
                                                            Calendar dateAddedFrom, Calendar dateAddedTo,
                                                            long disciplineId, Sort sort);
    @Transactional
     void deleteAppraisalsByDiscipline_Id(long id);

    //Methods for student
    List<Appraisal> findAppraisalByStudent_StudentNumber(long id);

    @Transactional
    void deleteAppraisalsByStudent_StudentNumber(long studentNumber);
}
