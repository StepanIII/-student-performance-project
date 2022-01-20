package com.stepan.pet_project.web_student_progress.service.appraisal_service;

import com.stepan.pet_project.web_student_progress.entity.Appraisal;

import java.util.Calendar;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface AppraisalService {
    List<Appraisal> getAllAppraisal();
    Appraisal getAppraisalById(long id);
    void deleteAppraisalById(long id);
    void saveOrUpdateAppraisal(Appraisal appraisal);

    List<Appraisal> getAppraisalByDisciplineId(long id);
    List<Appraisal> getAppraisalByStudentNumber(long id);

    List<Appraisal> getAppraisalByAttributesBySort(long studentNumber, String studentFullName, String discipline,
                                                   short scoreFrom, short scoreTo, Calendar dateAddedFrom,
                                                   Calendar dateAddedTo, String sortBy);

    List<Appraisal> getAppraisalByAttrAndDisciplineIdBySort(long studentNumber, String studentFullName, String discipline,
                                                            short scoreFrom, short scoreTo, Calendar dateAddedFrom,
                                                            Calendar dateAddedTo, long disciplineId, String sortBy);


    void deleteAppraisalsByStudentNumber(long studentNumber);
    void deleteAppraisalsByDiscipline_Id(long id);
}
