package com.aniket.taskman;

import com.aniket.taskman.entity.Student;
import com.aniket.taskman.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testStudentRepository() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

}
