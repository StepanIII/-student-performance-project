# web-student-progress

## Introduction
___
This is a web application for students and teachers that allows students to track their progress, and teachers to make changes and assign grades. It is also possible to select and sort data. There is a registration and authentication functionality. There are three categories of access rights student, teacher and administrator.
___
## Reauirement
- Java version 8 or higher
- MySql
- database created with the following template  https://github.com/StepanIII/web-student-progress/blob/master/db_creation.md
___

## Installing
___
1. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:
   
> git clone https://github.com/StepanIII/web-student-progress.git

2. Imported it in Intellij IDEA or any other Java IDE.
3. In the application.properties file, specify the necessary data to connect to MySql

## Usage
___
1. Run MySql with the created database which is given in the requirements.
2. Run the application.
3. Follow the link http://localhost:8080/login.
4. Register or log in or log in under one of the existing accounts:
    
    a. Login - Admin, password - admin. 
    
    b. Login - Teacher, password - teacher. 
    
    c. Login - Student, password - student. 
    
    ![picture](https://github.com/StepanIII/web-student-progress/blob/master/src/main/resources/Screen/1.png?raw=true, "registration")

5. On the main page, go to the desired section.


 ![picture](https://github.com/StepanIII/web-student-progress/blob/master/src/main/resources/Screen/2.png?raw=true, "home_page")

6. View, select and add the data we need    

![picture](https://github.com/StepanIII/web-student-progress/blob/master/src/main/resources/Screen/3.png?raw=true, "registration")
