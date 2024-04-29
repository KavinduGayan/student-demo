package com.example.studentdemo.controller;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting all students");
        }
        return studentService.findAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating new student: {}", student);
        }
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting student with id: {}", id);
        }
        return studentService.findStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        if (logger.isDebugEnabled()) {
            logger.debug("Updating student with id: {}", id);
        }
        return studentService.updateStudent(id, studentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Deleting student with id: {}", id);
        }
        studentService.deleteStudent(id);
    }
}
