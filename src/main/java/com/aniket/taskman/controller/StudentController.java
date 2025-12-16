package com.aniket.taskman.controller;

import com.aniket.taskman.dto.AddStudentDTO;
import com.aniket.taskman.dto.StudentDTO;
//import com.aniket.taskman.entity.Student;
//import com.aniket.taskman.repository.StudentRepository;
import com.aniket.taskman.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getStudent() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
//        return new StudentDTO(59L, "Aniket", "aniketsingh2151@gmail.com");
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentbyId(id));
    }

    @PostMapping("/student")
    public ResponseEntity<AddStudentDTO> createNewStudent(@RequestBody @Valid AddStudentDTO addStudentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentDTO));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteOneStudent(@PathVariable Long id) {
        studentService.deleteStudentbyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,
                                                    @RequestBody @Valid AddStudentDTO addStudentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, addStudentDTO));
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable Long id,
                                                           @RequestBody Map<String, Object> updates) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updatePartialStudent(id, updates));
    }
}
