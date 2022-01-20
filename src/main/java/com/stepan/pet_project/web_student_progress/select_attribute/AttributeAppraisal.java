package com.stepan.pet_project.web_student_progress.select_attribute;

import lombok.Data;
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
public class AttributeAppraisal implements Attribute{

    private long studentNumber;

    private String studentFullName;

    private String discipline;

    private short scoreFrom = 0;
    private short scoreTo = 5;

    private String dateAddedFrom = "1950-01-01";
    private String dateAddedTo = "2023-01-01";

    private String sort;

    private long disciplineId;

    public AttributeAppraisal() {
    }

    @Override
    public void resetAttributes() {
        this.studentNumber = 0;
        this.studentFullName = "";
        this.discipline = "";

        this.scoreFrom = 0;
        this.scoreTo = 5;

        this.dateAddedFrom = "1950-01-01";
        this.dateAddedTo = "2023-01-01";

        this.sort = "";
        this.disciplineId = 0;
    }

    @Override
    public boolean isSelected() {
        return this.sort == null || this.sort.isEmpty();
    }


    public Calendar getCalDateAddedFrom() {

        Date date = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dateAddedFrom.equals("")) {
                date = new Date();
            } else {
                date = df.parse(dateAddedFrom);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }

    public Calendar getCalDateAddedTo() {

        Date date = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (dateAddedTo.equals("")) {
                date = new Date();
            } else {
                date = df.parse(dateAddedTo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }


}
