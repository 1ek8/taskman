package com.aniket.taskman.controller;

import com.aniket.taskman.dto.StudentDTO;
import com.aniket.taskman.entity.Student;
import com.aniket.taskman.respository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public StudentDTO getStudentById() {
        return new StudentDTO(59L, "Aniket", "aniketsingh2151@gmail.com");
    }

}
