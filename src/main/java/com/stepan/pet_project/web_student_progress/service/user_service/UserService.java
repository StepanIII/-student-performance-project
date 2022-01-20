package com.stepan.pet_project.web_student_progress.service.user_service;

import com.stepan.pet_project.web_student_progress.entity.security_entity.User;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public interface UserService {
    User findByUsername(String username);

    void save(User user);
}
