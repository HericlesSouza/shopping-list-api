package com.shoppingList.shoppingListApi.domain.annotation;

import com.shoppingList.shoppingListApi.domain.annotation.validator.OptionalPositiveValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OptionalPositiveValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalPositive {
    String message() default "number must not be positive if present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
