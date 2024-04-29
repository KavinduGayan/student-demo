Certainly! Below is a sample `README.md` file for your Spring Boot project, which includes endpoints for managing `Student` objects using Maven, Spring Boot, H2 database, and JPA. This README will guide users on how to set up, run, and interact with the project.

---

# Student Management System

This is a simple Spring Boot application for managing student records. It uses Maven as the build tool, H2 as the in-memory database, and Spring Data JPA for CRUD operations. The application also includes RESTful endpoints to manage student entities.

## Features

- Create, Read, Update, and Delete student records.
- In-memory H2 database for easy setup and teardown.
- Swagger UI for API interaction.

## Prerequisites

- JDK 17
- Maven 3.6+

## Setting Up Locally

To get the application running locally, follow these steps:

### Clone the Repository

```bash
git clone git@github.com:KavinduGayan/student-demo.git
cd student-demo
```

### Build the Application

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application should now be running on `http://localhost:8969`.

## API Endpoints

All APIs can be accessed via `http://localhost:8969/students`. Below are the available endpoints:

- `GET /students`: List all students
- `POST /students`: Create a new student
    - Request body:
      ```json
      {
        "name": "test",
        "age": 22
      }
      ```
- `GET /students/{id}`: Get a student by ID
- `PUT /students/{id}`: Update a student by ID
    - Request body:
      ```json
      {
        "name": "test",
        "age": 23
      }
      ```
- `DELETE /students/{id}`: Delete a student by ID

## Database

The H2 console is accessible while the application is running at:

```
http://localhost:8969/h2-console
```

Login with the following details:
- **User Name**: `sa`
- **Password**: `sa`

## Testing

Run the following command to execute unit and integration tests:

```bash
mvn test
```