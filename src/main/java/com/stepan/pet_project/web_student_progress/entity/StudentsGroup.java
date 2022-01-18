package com.stepan.pet_project.web_student_progress.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "students_groups")
@Data
public class StudentsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "group_number")
    @Size(min = 5, max = 20, message = "Group number size should be min 5 symbols, max 20 symbols")
    @NotBlank(message = "Group number must not be empty")
    private String groupNumber;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            ,CascadeType.REFRESH})
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "studentsGroup")
    private List<Student> listStudents;

    @Column(name = "year_of_creation")
    private Calendar yearOfCreation;

    @Transient
  //  @Min(value = 0, message = "Faculty must not be empty")
    private long facultyId;

    @Transient
    private boolean fromFaculty;

    @Transient
    private boolean isFromUpdate;

    public StudentsGroup() {
        yearOfCreation = new GregorianCalendar();
    }

    public StudentsGroup(String groupNumber, Faculty faculty, Calendar yearOfCreation) {
        this.groupNumber = groupNumber;
        this.faculty = faculty;
        this.yearOfCreation = yearOfCreation;
    }

    public StudentsGroup(Faculty faculty) {
        this.faculty = faculty;
    }

    @Size(min = 4, max = 4, message = "Date format should be: yyyy")
    public String getYearOfCreation() {
        String result = "";
        if (yearOfCreation != null) {
            result = String.format("%d", yearOfCreation.get(Calendar.YEAR));
        }
        return result;
    }

    public void setYearOfCreation(String yearOfCreation) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy");

        try {
            date = df.parse(yearOfCreation);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        this.yearOfCreation = calendar;
    }
}
