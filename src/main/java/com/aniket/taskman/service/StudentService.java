package com.aniket.taskman.service;

import com.aniket.taskman.dto.AddStudentDTO;
import com.aniket.taskman.dto.StudentDTO;
//import com.aniket.taskman.entity.Student;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentbyId(Long id);

    AddStudentDTO createNewStudent(AddStudentDTO addStudentDTO);

    void deleteStudentbyId(Long id);
}
