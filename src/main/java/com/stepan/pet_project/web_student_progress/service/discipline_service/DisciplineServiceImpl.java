package com.stepan.pet_project.web_student_progress.service.discipline_service;

import com.stepan.pet_project.web_student_progress.dao.DisciplineRepository;
import com.stepan.pet_project.web_student_progress.entity.Discipline;
import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    DisciplineRepository disciplineRepository;

    @Override
    public List<Discipline> getAllDiscipline() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline getDisciplineById(long id) {
        Discipline discipline = null;

        Optional<Discipline> optionalDiscipline = disciplineRepository.findById(id);

        if (optionalDiscipline.isEmpty()) try {
            throw new Exception("Discipline with id =" + id + " is not in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

        discipline = optionalDiscipline.get();

        return discipline;
    }

    @Override
    public void deleteDisciplineById(long id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public List<Discipline> getDisciplineByAttributesBySort(String disciplineName, int hourFrom, int hourTo, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        return disciplineRepository.getDisciplineByAttributesBySort(disciplineName, hourFrom, hourTo, sort);
    }

    @Override
    public Integer getMaxHour() {
        return disciplineRepository.getMaxHour();
    }
}
