package com.stepan.pet_project.web_student_progress.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_number")
    private long studentNumber;

    @Column(name = "full_name")
    @Size(min = 5, max = 30, message = "Student full name size should be min 5 symbols and max 30 symbols")
    @NotBlank(message = "Student full name must not be empty")
    private String fullName;

    @Column(name = "exam_points")
    @Min(value = 150, message = "Min exam point must be 150")
    @Max(value = 300, message = "Min exam point must be 300")
    private short examPoints;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            ,CascadeType.REFRESH})
    @JoinColumn(name = "students_group_id")
    private StudentsGroup studentsGroup;

    @Column(name = "date_of_birth")
    private Calendar dateOfBirth;

    @Column(name = "city")
    @Size(min = 5, max = 30, message = "Student city size should be min 5 symbols and max 30 symbols")
    @NotBlank(message = "Student city must not be empty")
    private String city;

    @Column(name = "scholarship")
    @Max(value = 30000, message = "Max scholarship must be 30000")
    private float scholarship;

    @Transient
    private String facultyName;

    @Transient
    @Min(value = 1, message = "Group must not be empty")
    private long groupId;

    @Transient
    private long facultyId;

    @Transient
    private boolean fromGroup;

    @Transient
    private boolean fromFaculty;

    @Transient
    private boolean isFromUpdate;

    public Student() {
        this.dateOfBirth = new GregorianCalendar();
    }

    public Student(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
        this.dateOfBirth = new GregorianCalendar();
    }

    @Size(min = 10, max = 10, message = "Date format should be: yyyy-MM-dd")
    public String getDateOfBirth() {
        String result = "";
        String year = String.valueOf(dateOfBirth.get(Calendar.YEAR));
        String month = String.valueOf(dateOfBirth.get(Calendar.MONTH) + 1);
        String date = String.valueOf(dateOfBirth.get(Calendar.DATE));

        if (month.length() < 2) month = 0 + month;
        if (date.length() < 2) date = 0 + date;

        if (dateOfBirth != null) {
            result = String.format("%s-%s-%s", year, month, date);
        }
        return result;
    }

    public void setDateOfBirth(String birth) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        this.dateOfBirth = calendar;
    }

}
