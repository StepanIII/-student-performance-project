package com.stepan.pet_project.web_student_progress.select_attribute;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Component
@Data
public class AttributeFaculty implements Attribute{

    private String facultyName;
    private String deanFullName;
    private String sort;

    public AttributeFaculty() {
    }

    @Override
    public boolean isSelected() {
        return this.sort == null || this.sort.isEmpty();
    }

    @Override
    public void resetAttributes() {
        this.facultyName = "";
        this.deanFullName = "";
        this.sort = "";
    }
}
