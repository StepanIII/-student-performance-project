package com.stepan.pet_project.web_student_progress.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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

@Entity
@Table(name = "appraisals")
@Data
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            ,CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            ,CascadeType.REFRESH})
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Column(name = "score")
    @Min(value = 1, message = "Min score must be 1")
    @Max(value = 5, message = "Max score must be 5")
    private short score;

    @Column(name = "date_added")
    private Calendar dateAdded;

    @Transient
    @Min(value = 1, message = "Student must not be empty")
    private long studentNumber;

    @Transient
    private long disciplineId;

    @Transient
    private boolean isFromUpdate;

    public Appraisal() {
        this.dateAdded = new GregorianCalendar();
    }

    public Appraisal(Student student, Discipline discipline) {
        this.student = student;
        this.discipline = discipline;
        this.dateAdded = new GregorianCalendar();
    }

    @Size(min = 10, max = 10, message = "Date format should be: yyyy-MM-dd")
    public String getDateAdded() {
        String result = "";
        String year = String.valueOf(dateAdded.get(Calendar.YEAR));
        String month = String.valueOf(dateAdded.get(Calendar.MONTH) + 1);
        String date = String.valueOf(dateAdded.get(Calendar.DATE));

        if (month.length() < 2) month = 0 + month;
        if (date.length() < 2) date = 0 + date;

        if (dateAdded != null) {
            result = String.format("%s-%s-%s", year, month, date);
        }
        return result;
    }

    public void setDateAdded(String birth) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        this.dateAdded = calendar;
    }
}
