package com.stepan.pet_project.web_student_progress.service.authority_service;


import com.stepan.pet_project.web_student_progress.entity.security_entity.Role;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface RoleService {
    Role findByRole(String roleName);
}
