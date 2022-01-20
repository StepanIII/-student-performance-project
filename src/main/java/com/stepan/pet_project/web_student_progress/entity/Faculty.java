package com.stepan.pet_project.web_student_progress.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Entity
@Table(name = "faculties")
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "faculty_name")
    @NotBlank(message = "Faculty name must be not empty")
    @Size(min = 5, max = 30, message = "Faculty name must be min 3 symbols, max 30 symbols")
    private String facultyName;

    @Column(name = "dean")
    @NotBlank(message = "Dean name must be not empty")
    @Size(min = 5, max = 30, message = "Dean name must be min 5 symbols, max 30 symbols")
    private String dean;

    @OneToMany(cascade = CascadeType.ALL
            ,mappedBy = "faculty")
    private List<StudentsGroup> studentsGroups;

    @Transient
    private boolean isFromUpdate;


    public Faculty() {
    }

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

}
