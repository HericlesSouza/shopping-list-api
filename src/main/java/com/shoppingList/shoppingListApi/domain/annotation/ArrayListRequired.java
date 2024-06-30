package com.shoppingList.shoppingListApi.domain.annotation;

import com.shoppingList.shoppingListApi.domain.annotation.validator.ArrayListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ArrayListValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayListRequired {
    String message() default "must be an array list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
