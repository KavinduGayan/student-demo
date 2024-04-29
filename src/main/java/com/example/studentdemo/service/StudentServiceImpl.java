package com.example.studentdemo.service;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        try {
            return studentRepository.findById(id).orElseThrow(() -> {
                NoSuchElementException e = new NoSuchElementException("Student not found with id: " + id);
                logger.error("Error finding student: {}", e.getMessage());
                return e;
            });
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}