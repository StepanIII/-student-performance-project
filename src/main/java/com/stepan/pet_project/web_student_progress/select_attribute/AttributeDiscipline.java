package com.stepan.pet_project.web_student_progress.select_attribute;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AttributeDiscipline implements Attribute{

    private String disciplineName;
    private int hourFrom;
    private int hourTo;
    private String sort;

    public AttributeDiscipline() {
        disciplineName = "";
        this.hourFrom = 0;
        this.hourTo = 250;
        this.sort = "";
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
