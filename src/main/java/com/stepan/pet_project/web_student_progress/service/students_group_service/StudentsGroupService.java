package com.stepan.pet_project.web_student_progress.service.students_group_service;

import com.stepan.pet_project.web_student_progress.entity.StudentsGroup;

import java.util.Calendar;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface StudentsGroupService {

    List<StudentsGroup> getAllGroups();

    StudentsGroup getGroupById(long id);

    void deleteGroupById(long id);

    void saveOrUpdateGroup(StudentsGroup studentsGroup);

    List<StudentsGroup> getGroupsForAttributesBySort(String groupNumber, String facultyName, Calendar yearFrom, Calendar yearTo, long facultyId, String sortBy);

    List<StudentsGroup> getGroupsByFacultyId(long id);

    List<StudentsGroup> getStudentsGroupsByNumber(String groupNumber);

}
