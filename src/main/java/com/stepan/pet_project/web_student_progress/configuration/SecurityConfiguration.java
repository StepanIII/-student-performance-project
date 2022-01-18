package com.stepan.pet_project.web_student_progress.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u_r.username, u_r.role_name from \n" +
                        "(select users.username as username, roles.role_name as role_name from users, roles, authorities\n" +
                        "where authorities.user_id = users.id and authorities.role_id = roles.id) as u_r\n" +
                        "where u_r.username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().
                antMatchers("/").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                .antMatchers("/faculty/add", "/faculty/update", "/faculty/delete").hasRole("ADMIN")
                .antMatchers("/students_group/add", "/students_group/update", "/students_group/delete").hasRole("ADMIN")
                .antMatchers("/student/add", "/student/update", "/student/delete").hasRole("ADMIN")
                .antMatchers("/discipline/add", "/discipline/update", "/discipline/delete").hasRole("ADMIN")
                .antMatchers("/appraisal/add", "/appraisal/update", "/appraisal/delete").hasAnyRole("ADMIN", "TEACHER")
                .and().formLogin().loginPage("/login").permitAll();
    }
}
