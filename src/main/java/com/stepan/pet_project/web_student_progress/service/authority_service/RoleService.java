package com.stepan.pet_project.web_student_progress.service.authority_service;


import com.stepan.pet_project.web_student_progress.entity.security_entity.Role;

public interface RoleService {
    Role findByRole(String roleName);
}
