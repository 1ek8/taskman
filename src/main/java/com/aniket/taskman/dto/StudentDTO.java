package com.aniket.taskman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //creates boilerplate for getter-setter, constructor, others
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;

}
