package com.stepan.pet_project.web_student_progress.select_attribute;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Component
@Data
public class AttributeDiscipline implements Attribute{

    private String disciplineName;
    private int hourFrom;
    private int hourTo = 250;
    private String sort;

    public AttributeDiscipline() {
    }

    @Override
    public boolean isSelected() {
        return this.sort == null || this.sort.isEmpty();
    }

    @Override
    public void resetAttributes() {
        disciplineName = "";
        this.hourFrom = 0;
        this.hourTo = 250;
        this.sort = "";
    }
}
