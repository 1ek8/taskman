package com.aniket.taskman.annotaitons;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StudentUsernameValidator implements ConstraintValidator<StudentRoleValidation, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isEmpty();
    }
}
