package com.aniket.taskman.dto;

import com.aniket.taskman.annotaitons.StudentUsernameValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequestDTO {

    @StudentUsernameValidation
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

}
