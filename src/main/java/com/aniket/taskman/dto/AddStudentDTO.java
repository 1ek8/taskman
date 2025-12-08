package com.aniket.taskman.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDTO {

    @NotBlank(message="name field is required")
    @Size(min = 3, max = 20, message = "length of name should be between 3 to 20 characters")
    public String name;

    @Email
    @NotBlank(message="email field is required")
    public String email;
}
