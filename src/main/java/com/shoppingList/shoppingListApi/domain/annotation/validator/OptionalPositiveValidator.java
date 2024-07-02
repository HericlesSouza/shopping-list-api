package com.shoppingList.shoppingListApi.domain.annotation.validator;

import com.shoppingList.shoppingListApi.domain.annotation.OptionalPositive;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalPositiveValidator implements ConstraintValidator<OptionalPositive, Integer> {
    @Override
    public void initialize(OptionalPositive optionalPositive) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == null || value > 0;
    }
}
