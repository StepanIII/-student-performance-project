- ## Database creation


``` SQL
CREATE DATABASE `student_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */
```
___

- ## Tables creation 
  
    ``` SQL
  CREATE TABLE `faculties` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(45) NOT NULL,
  `dean` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
  ```
``` SQL
CREATE TABLE `students_groups` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_number` varchar(45) NOT NULL,
  `faculty_id` int NOT NULL,
  `year_of_creation` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `group_number_UNIQUE` (`group_number`),
  KEY `faculty_id_idx` (`faculty_id`),
  CONSTRAINT `faculty_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
  ``` SQL
  CREATE TABLE `disciplines` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discipline_name` varchar(45) NOT NULL,
  `hour` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `discipline_name_UNIQUE` (`discipline_name`)) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
  ```

  ``` SQL 
CREATE TABLE `students` (
  `student_number` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) NOT NULL,
  `exam_points` int NOT NULL,
  `students_group_id` int NOT NULL,
  `date_of_birth` date NOT NULL,
  `city` varchar(45) NOT NULL,
  `scholarship` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`student_number`),
  UNIQUE KEY `student_number_UNIQUE` (`student_number`),
  KEY `student_group_id_idx` (`students_group_id`),
  CONSTRAINT `student_group_id` FOREIGN KEY (`students_group_id`) REFERENCES `students_groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

  ``` SQL 
  CREATE TABLE `appraisals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `discipline_id` int NOT NULL,
  `score` int NOT NULL,
  `date_added` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `student_id_idx` (`student_id`),
  KEY `discipline_id_idx` (`discipline_id`),
  CONSTRAINT `discipline_id` FOREIGN KEY (`discipline_id`) REFERENCES `disciplines` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_number`))ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
  ```

``` SQL
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

``` SQL
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
  ```SQL
  CREATE TABLE `authorities` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
  ```
___
- ## Adding data for permissions

``` SQL 
INSERT INTO student_db.users(username, password)
VALUES('Admin','admin'),
('Teacher','teacher'),
('Student','student');
```

``` SQL
INSERT INTO student_db.roles(role_name)
values('ROLE_ADMIN'),
('ROLE_STUDENT'),
('ROLE_TEACHER');
```

``` SQL
INSERT INTO student_db.authorities(user_id, role_id)
values(1,1),
(2,2),
(3,3);
```