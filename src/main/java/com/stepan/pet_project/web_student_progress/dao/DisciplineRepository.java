package com.stepan.pet_project.web_student_progress.dao;

import com.stepan.pet_project.web_student_progress.entity.Discipline;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    @Transactional
    @Query("select discipline from Discipline as discipline " +
            "where discipline.disciplineName like %:disciplineName% " +
            "and discipline.hour between :hourFrom and :hourTo")
    List<Discipline> getDisciplineByAttributesBySort(String disciplineName, int hourFrom, int hourTo, Sort sort);

    @Transactional
    @Query("select max(discipline.hour) from Discipline as discipline")
    Integer getMaxHour();
}
