package com.stepan.pet_project.web_student_progress.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Entity
@Table(name = "disciplines")
@Data
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "discipline_name")
    @Size(min = 5, max = 50, message = "Discipline name size should be min 5 symbols and max 50 symbols")
    @NotBlank(message = "Discipline name must not be empty")
    private String disciplineName;

    @Column(name = "hour")
    @Min(value = 100, message = "The number of hours must not be less than 100")
    @Max(value = 200, message = "The number of hours must not be more than 200")
    private int hour;

    @Transient
    private boolean isFromUpdate;

    public Discipline() {
    }

    public Discipline(String disciplineName, int hour) {
        this.disciplineName = disciplineName;
        this.hour = hour;
    }
}
