package com.stepan.pet_project.web_student_progress.select_attribute;

import com.stepan.pet_project.web_student_progress.service.students_group_service.StudentsGroupService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@Component
public class AttributeStudentsGroup implements Attribute{

    @Autowired
    private StudentsGroupService studentsGroupService;

    private String groupNumber;
    private String facultyName;

    private String yearFrom;
    private String yearTo;

    private String sort;
    private long facultyId;
    private boolean fromUpdate;

    public AttributeStudentsGroup() {
        this.groupNumber = "";
        this.facultyName = "";

        this.yearFrom = "1950";
        this.yearTo = "2023";

        this.sort = "";
        this.facultyId = 0;
        this.fromUpdate = false;
    }

    public Calendar getCalendarFrom() {

        Date date = null;

        DateFormat df = new SimpleDateFormat("yyyy");
        try {
            if (yearFrom.equals("")) {
                date = new Date();
            } else {
                date = df.parse(yearFrom);
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

        DateFormat df = new SimpleDateFormat("yyyy");
        try {
            if (yearTo.equals("")) {
                date = new Date();
            } else {
                date = df.parse(yearTo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        return calendar;
    }

    public void resetAttributes() {
        this.groupNumber = "";
        this.facultyName = "";

        this.yearFrom = "1950";
        this.yearTo = "2023";

        this.sort = "";
        this.facultyId = 0;
        this.fromUpdate = false;
    }

    @Override
    public boolean isSelected() {
        return this.sort == null || this.sort.isEmpty();
    }

}
