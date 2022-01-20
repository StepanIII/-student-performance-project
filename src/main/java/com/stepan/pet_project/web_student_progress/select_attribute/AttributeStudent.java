package com.stepan.pet_project.web_student_progress.select_attribute;

import com.stepan.pet_project.web_student_progress.service.student_service.StudentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Component
@Data
public class AttributeStudent implements Attribute{

    @Autowired
    private StudentService studentService;

    private long studentNumber;

    private String fullName;

    private short examPointsFrom;
    private short examPointsTo = 300;

    private String studentGroupName;
    private String facultyName;

    private String dateOfBirthFrom = "1950-01-01";;
    private String dateOfBirthTo = "2023-01-01";;

    private String city;

    private float scholarshipFrom;
    private float scholarshipTo = 30000;

    private String sort;

    private long groupId;
    private long facultyId;

    public AttributeStudent() {
    }

    public Calendar getCalendarFrom() {

        Date date = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dateOfBirthFrom.equals("")) {
                date = new Date();
            } else {
                date = df.parse(dateOfBirthFrom);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }

    public Calendar getCalendarTo() {

        Date date = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dateOfBirthTo.equals("")) {
                date = new Date();
            } else {
                date = df.parse(dateOfBirthTo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }


    @Override
    public boolean isSelected() {
        return this.sort == null || this.sort.isEmpty();
    }

    @Override
    public void resetAttributes() {
        this.studentNumber = 0;
        this.fullName = "";

        this.examPointsFrom = 0;
        this.examPointsTo = 300;

        this.dateOfBirthFrom = "1950-01-01";
        this.dateOfBirthTo = "2023-01-01";

        this.scholarshipFrom = 0;
        this.scholarshipTo = 30000;

        this.studentGroupName = "";
        this.facultyName = "";

        this.city = "";

        this.sort = "";

        this.groupId = 0;
        this.facultyId = 0;
    }
}
