package com.aniket.taskman.controller;

import com.aniket.taskman.dto.StudentDTO;
//import com.aniket.taskman.entity.Student;
//import com.aniket.taskman.repository.StudentRepository;
import com.aniket.taskman.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

    @GetMapping("/student")
    public List<StudentDTO> getStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
//        return new StudentDTO(59L, "Aniket", "aniketsingh2151@gmail.com");
        return studentService.getStudentbyId(id);
    }

}
