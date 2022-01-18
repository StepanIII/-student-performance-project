package com.stepan.pet_project.web_student_progress.service.appraisal_service;

import com.stepan.pet_project.web_student_progress.dao.AppraisalRepository;
import com.stepan.pet_project.web_student_progress.entity.Appraisal;
import com.stepan.pet_project.web_student_progress.select_attribute.MySort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AppraisalServiceImpl implements AppraisalService{

    @Autowired
    private AppraisalRepository appraisalRepository;

    @Override
    public List<Appraisal> getAllAppraisal() {
        return appraisalRepository.findAll();
    }

    @Override
    public Appraisal getAppraisalById(long id) {
        Appraisal appraisal;
        Optional<Appraisal> optionalAppraisal = appraisalRepository.findById(id);
        appraisal = optionalAppraisal.get();
        return appraisal;
    }

    @Override
    public void deleteAppraisalById(long id) {
        appraisalRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdateAppraisal(Appraisal appraisal) {
        appraisalRepository.save(appraisal);
    }

    @Override
    public List<Appraisal> getAppraisalByDisciplineId(long id) {
        return appraisalRepository.findAppraisalByDiscipline_Id(id);
    }

    @Override
    public List<Appraisal> getAppraisalByStudentNumber(long id) {
        return appraisalRepository.findAppraisalByStudent_StudentNumber(id);
    }

    @Override
    public List<Appraisal> getAppraisalByAttributesBySort(long studentNumber, String studentFullName, String discipline,
                                                          short scoreFrom, short scoreTo, Calendar dateAddedFrom,
                                                          Calendar dateAddedTo, String sortBy) {
       Sort sort = MySort.getSortByPassedArgument(sortBy);
        if(studentNumber == 0){
            return appraisalRepository.getAppraisalByAttributesBySort(studentFullName, discipline, scoreFrom, scoreTo,
                                                                     dateAddedFrom, dateAddedTo, sort);
        }
        return appraisalRepository.getAppraisalByAttributesBySort(studentNumber, studentFullName, discipline, scoreFrom,
                                                                  scoreTo,dateAddedFrom, dateAddedTo, sort);
    }

    @Override
    public List<Appraisal> getAppraisalByAttrAndDisciplineIdBySort(long studentNumber, String studentFullName,
                                                                   String discipline, short scoreFrom, short scoreTo,
                                                                   Calendar dateAddedFrom, Calendar dateAddedTo,
                                                                   long disciplineId, String sortBy) {
        Sort sort = MySort.getSortByPassedArgument(sortBy);

        if(studentNumber == 0){
            return appraisalRepository.getAppraisalByAttrAndDisciplineIdBySort(studentFullName, discipline, scoreFrom,
                                                                                scoreTo, dateAddedFrom, dateAddedTo,
                                                                                disciplineId, sort);
        }
        return appraisalRepository.getAppraisalByAttrAndDisciplineIdBySort(studentNumber, studentFullName, discipline,
                                                                            scoreFrom, scoreTo, dateAddedFrom, dateAddedTo,
                                                                            disciplineId, sort);
    }

    @Override
    public void deleteAppraisalsByStudentNumber(long studentNumber) {
        appraisalRepository.deleteAppraisalsByStudent_StudentNumber(studentNumber);
    }

    @Override
    public void deleteAppraisalsByDiscipline_Id(long id) {
        appraisalRepository.deleteAppraisalsByDiscipline_Id(id);
    }


}
