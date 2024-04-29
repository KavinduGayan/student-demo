package com.example.studentdemo;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @Test
    void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student("John Doe", 20);
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void testGetStudentById() throws Exception {
        Long studentId = 1L;
        Student student = new Student("John Doe", 20);
        student.setId(studentId);
        when(studentService.findStudentById(studentId)).thenReturn(student);

        mockMvc.perform(get("/students/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student("John Doe", 20);
        student.setId(studentId);
        when(studentService.updateStudent(eq(studentId), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/" + studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Long studentId = 1L;
        doNothing().when(studentService).deleteStudent(studentId);

        mockMvc.perform(delete("/students/" + studentId))
                .andExpect(status().isOk());
    }
}
