package com.stepan.pet_project.web_student_progress.entity.security_entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Entity
@Table(name = "users")
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST
            ,CascadeType.REFRESH})
    @JoinTable(name = "authorities"
                    ,joinColumns = @JoinColumn(name = "user_id")
                    ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;

    public User() {
    }

}