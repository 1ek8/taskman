package com.aniket.taskman.repository;


import com.aniket.taskman.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);

    List<Student> findByNameOrEmail(String name, String email);
}
