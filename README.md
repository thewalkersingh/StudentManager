# Student Manager

A Spring Boot-based application designed to manage students, courses, and enrollments with dedicated functionalities for
both admin and student users.

- Admin Features: Admit new students, manage courses, and oversee enrollments. (Admin credentials are hardcoded.)
- Student Features: Self-service capabilities including profile management and course interactions.
- Testing Data: Dummy data has been added for testing purposes.
  All details and API documentation are available below. I plan to continue enhancing this project and would appreciate
  your feedback.

For suggestions or inquiries, feel free to reach me at: `the.walkersingh@hotmail.com`

### Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Postman Collection](#postman-collection)

## Implemented Features

- **Admin Section:**
    - Admit new students with personal details and multiple addresses.
    - Upload course details and assign courses to students.
    - Search for students by name.
    - Get list of students enrolled in a specific course.

- **Student Section:**
    - Validate student credentials using student ID and date of birth.
    - Update profile details including email, mobile number, parent's info, and addresses.
    - View assigned courses with search functionality.
    - Leave a course.

## Technologies Used

- **Spring Boot** for REST API and backend services.
- **Spring Data JPA** with Hibernate for ORM.
- **MapStruct** for DTO-entity mapping.
- **Spring Security** for securing admin endpoints.
- **MySQL** as the database.
- **Lombok** for reducing boilerplate code.
- **Swagger/OpenAPI** for API documentation.
- **JUnit & Mockito** for testing.

## Project Structure

```plaintext
studentmanager/
├── pom.xml
└── src
    /main/java/com/studentmanagement
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    ├── mapper
    ├── config 
    /test
      ├── controller
      │   └── StudentControllerIntegrationTest.java
      └── service
          └── StudentServiceTest.java                
```

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/thewalkersingh/studentmanager.git
   cd studentmanager

   2. Update src/main/resources/application.yml with your MySQL connection details
       ```
      spring:
        datasource:
         url: jdbc:mysql://localhost:3306/studentdb?createDatabaseIfNotExist=true
         username: your_username
         password: your_password
         driver-class-name: com.mysql.cj.jdbc.Driver

      jpa:
        database-platform: org.hibernate.dialect.MySQL8Dialect
        hibernate:
          ddl-auto: update
        show-sql: true
      ```

3. Build the Project
    ```bash
   mvn clean install
4. Run the Application
    ```bash
    mvn spring-boot:run

## API Endpoints

### 1. Admin Endpoints

- POST /api/admin/students/admit
- GET /api/admin/students/search?name={name}
- PUT /api/admin/students/{studentId}/courses/{courseId}
- POST /api/admin/courses/upload
- GET /api/admin/courses
- GET /api/admin/courses/{courseId}/students

### 2. Student Endpoints

- POST /api/students/validate
- PUT /api/students/update/{studentId}
- GET /api/students/courses
- POST /api/students/leave/{courseId}

## Testing

#### 1. Unit Tests

- Run `mvn test` to execute unit tests for the service layer.

#### 2. Integration Tests

- Integration tests for controllers are also available under `src/test/java`.

## Postman Collection

Import the provided Postman collection into Postman for a quick start on API testing. Alternatively, follow the
API testing instructions above to manually test the endpoints.\
https://student-management-system-5474.postman.co/workspace/092c4dca-17e7-478f-98dd-da22b9d7087a

- I have used below details for testing purpose.
- Use `Basic Auth` with
  `Username: admin` and
  `Password: admin123`
