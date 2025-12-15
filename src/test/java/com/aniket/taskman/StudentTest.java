package com.aniket.taskman;

import com.aniket.taskman.entity.Student;
import com.aniket.taskman.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional //when not added, db calls are treated as individuals. this annotation clubs together all the calls and treats them all as a single unit
    public void testGetStudentbyId(){

        Long id = 1L;
        Student s1 = studentRepository.findById(id).orElseThrow();

        Student s2 = studentRepository.findById(id).orElseThrow();

        s1.setName("yoo"); //this name is updated without save action because this name gets saved in the persistent state and that state ends up being shipped along with the rest without rollback

//        studentRepository.save(s1);

    }

    @Test
    public void testDerivedQuery() {

        Student student = studentRepository.findByName("smoke");
        List<Student> studentList= studentRepository.findByNameOrEmail("smoke", "borat@kazak.com");
        System.out.println(student);
        System.out.println(studentList);
    }

}
