package com.stepan.pet_project.web_student_progress.dao;


import com.stepan.pet_project.web_student_progress.entity.security_entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
