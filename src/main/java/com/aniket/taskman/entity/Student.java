package com.aniket.taskman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(
        name = "student_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_student_email", columnNames = {"email"}) //now two students with same email cannot coexist in this table, uniqueConstratints bring down get queries' speed so need to be planned strategically
        },
        indexes = {
                @Index(name = "idx_student_email", columnList = "email") // makes fetch queries faster by creating an index (extra memory)
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="student_table_name", nullable = false)
    private String name;

    @ToString.Exclude
    private String email;

}

