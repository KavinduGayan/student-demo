package com.example.studentdemo;

import com.example.studentdemo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/students";
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<List> response = restTemplate.getForEntity(baseUrl, List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student("test", 20);
        ResponseEntity<Student> response = restTemplate.postForEntity(baseUrl, student, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(student.getName());
    }

    @Test
    public void testGetStudentById() {
        // Assuming there is a student with ID 1
        ResponseEntity<Student> response = restTemplate.getForEntity(baseUrl + "/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        Student updatedStudent = new Student("test", 22);
        HttpEntity<Student> requestEntity = new HttpEntity<>(updatedStudent);
        ResponseEntity<Student> response = restTemplate.exchange(baseUrl + "/1", HttpMethod.PUT, requestEntity, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(updatedStudent.getName());
    }

    @Test
    public void testDeleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/1", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
