package com.stepan.pet_project.web_student_progress.service.user_service;

import com.stepan.pet_project.web_student_progress.dao.UserRepository;
import com.stepan.pet_project.web_student_progress.entity.security_entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
