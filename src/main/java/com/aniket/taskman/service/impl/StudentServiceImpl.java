package com.aniket.taskman.service.impl;

import com.aniket.taskman.dto.AddStudentDTO;
import com.aniket.taskman.dto.StudentDTO;
import com.aniket.taskman.entity.Student;
import com.aniket.taskman.repository.StudentRepository;
import com.aniket.taskman.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor //don't need to make a constructor for all variables declared as final
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
//                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail()))
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    @Override
    public StudentDTO getStudentbyId(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not Found for given id"));
        StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);
        return studentDto;
    }

    @Override
    public AddStudentDTO createNewStudent(AddStudentDTO addStudentDTO) {
        Student newStudent = modelMapper.map(addStudentDTO, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, AddStudentDTO.class);
    }

    @Override
    public void deleteStudentbyId(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with " + id + " as ID doesn't seem to exist in DB");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO) {
        Student student = studentRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Student with ID: " + id + "not found in DB"));
        modelMapper.map(addStudentDTO, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("Student with ID: " + id + "not found in DB"));
        updates.forEach((field, value) -> {
           switch (field){
               case "name" :
                   student.setName((String) value);
                   break;
               case "email" :
                   student.setEmail((String) value);
                   break;
               default: throw new IllegalArgumentException("Field not supported");
           }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }
}