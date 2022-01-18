package com.stepan.pet_project.web_student_progress.service.students_group_service;

import com.stepan.pet_project.web_student_progress.entity.Student;
import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;

import java.util.Calendar;
import java.util.List;

public interface StudentsGroupService {
    //Methods for StudentsGroup
    List<StudentsGroup> getAllGroups();

    StudentsGroup getGroupById(long id);

    void deleteGroupById(long id);

    void saveOrUpdateGroup(StudentsGroup studentsGroup);

    List<StudentsGroup> getGroupsForAttributesBySort(String groupNumber, String facultyName, Calendar yearFrom, Calendar yearTo, long facultyId, String sortBy);

    List<StudentsGroup> getGroupsByFacultyId(long id);

    List<StudentsGroup> getStudentsGroupsByNumber(String groupNumber);

    String getMinYearOfCreation();
    String getMaxYearOfCreation();
}
