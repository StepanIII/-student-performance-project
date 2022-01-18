package com.stepan.pet_project.web_student_progress.service.facultie_service;

import com.stepan.pet_project.web_student_progress.dao.FacultyRepository;
import com.stepan.pet_project.web_student_progress.entity.Faculty;
import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(long id) {
        Faculty faculty = null;

        Optional<Faculty> optionalFaculty= facultyRepository.findById(id);

        if (optionalFaculty.isEmpty()) try {
            throw new Exception("Faculty with id =" + id + " is not in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

        faculty = optionalFaculty.get();

        return faculty;
    }

    @Override
    public void deleteFacultyById(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> getFacultiesByAttributesBySort(String facultyName, String deanFullName, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        return facultyRepository.getFacultiesByAttributesBySort(facultyName, deanFullName, sort);
    }

    @Override
    public List<Student> getStudentsByFaculty(long id) {
        return facultyRepository.getStudentsByFaculty(id);
    }

    @Override
    public List<StudentsGroup> getStudentsGroupsByFacultyId(long id) {
        return facultyRepository.getStudentsGroupsByFacultyId(id);
    }


}
