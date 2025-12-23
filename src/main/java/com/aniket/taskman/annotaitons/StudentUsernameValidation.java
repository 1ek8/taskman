package com.aniket.taskman.annotaitons;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {StudentUsernameValidator.class})
public @interface StudentUsernameValidation {
    String message() default "Username of Student cannot be null";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
