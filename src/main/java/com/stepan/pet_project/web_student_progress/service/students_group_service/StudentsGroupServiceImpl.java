package com.stepan.pet_project.web_student_progress.service.students_group_service;

import com.stepan.pet_project.web_student_progress.dao.StudentsGroupRepository;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;
import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsGroupServiceImpl implements StudentsGroupService{

    @Autowired
    private StudentsGroupRepository studentsGroupRepository;

    @Override
    public List<StudentsGroup> getAllGroups() {
        return studentsGroupRepository.findAll();
    }

    //Methods for StudentsGroup
    @Override
    public StudentsGroup getGroupById(long id) {
        StudentsGroup studentsGroup = null;
        Optional<StudentsGroup> optionalStudentsGroup = studentsGroupRepository.findById(id);

        if (optionalStudentsGroup.isEmpty()) try {
            throw new Exception("StudentsGroup with id =" + id + " is not in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentsGroup = optionalStudentsGroup.get();

        return studentsGroup;
    }

    @Override
    public void deleteGroupById(long id) {
        studentsGroupRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateGroup(StudentsGroup studentsGroup) {
        studentsGroupRepository.save(studentsGroup);
    }

    @Override
    public List<StudentsGroup> getGroupsForAttributesBySort(String groupNumber, String facultyName, Calendar yearFrom, Calendar yearTo, long facultyId, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);
        if(facultyId == 0) {
            return studentsGroupRepository.getGroupsForAttributesBySort(groupNumber, facultyName, yearFrom, yearTo, sort);
        } else {
            return studentsGroupRepository.getGroupsForAttributesAndFacultyIdBySort(groupNumber, facultyName, yearFrom, yearTo, facultyId, sort);
        }
    }


    @Override
    public List<StudentsGroup> getGroupsByFacultyId(long id) {
        return studentsGroupRepository.findStudentsGroupByFaculty_Id(id);
    }

    @Override
    public List<StudentsGroup> getStudentsGroupsByNumber(String groupNumber) {
        return studentsGroupRepository.getStudentsGroupsByNumber(groupNumber);
    }

    @Override
    public String getMinYearOfCreation() {
        int year = studentsGroupRepository.getMinYearOfCreation().get(Calendar.YEAR);
        return String.valueOf(year);
    }

    @Override
    public String getMaxYearOfCreation() {
        int year = studentsGroupRepository.getMaxYearOfCreation().get(Calendar.YEAR);
        return String.valueOf(year);
    }
}
