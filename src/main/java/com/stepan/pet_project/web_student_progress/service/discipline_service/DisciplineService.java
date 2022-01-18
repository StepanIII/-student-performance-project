package com.stepan.pet_project.web_student_progress.service.discipline_service;

import com.stepan.pet_project.web_student_progress.entity.Discipline;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DisciplineService {
    List<Discipline> getAllDiscipline();

    Discipline getDisciplineById(long id);

    void deleteDisciplineById(long id);

    void saveOrUpdateDiscipline(Discipline discipline);

    List<Discipline> getDisciplineByAttributesBySort(String disciplineName, int hourFrom, int hourTo, String sortBy);

    Integer getMaxHour();
}
