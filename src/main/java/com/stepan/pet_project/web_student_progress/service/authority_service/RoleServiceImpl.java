package com.stepan.pet_project.web_student_progress.service.authority_service;

import com.stepan.pet_project.web_student_progress.dao.RoleRepository;

import com.stepan.pet_project.web_student_progress.entity.security_entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
