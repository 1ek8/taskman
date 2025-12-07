package com.aniket.taskman.service;

import com.aniket.taskman.dto.AddStudentDTO;
import com.aniket.taskman.dto.StudentDTO;
import org.jspecify.annotations.Nullable;
//import com.aniket.taskman.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentbyId(Long id);

    AddStudentDTO createNewStudent(AddStudentDTO addStudentDTO);

    void deleteStudentbyId(Long id);

    StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO);

    StudentDTO updatePartialStudent(Long id, Map<String, Object> updates);
}
