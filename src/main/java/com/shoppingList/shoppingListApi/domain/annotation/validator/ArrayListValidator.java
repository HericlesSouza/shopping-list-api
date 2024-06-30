package com.shoppingList.shoppingListApi.domain.annotation.validator;

import com.shoppingList.shoppingListApi.domain.annotation.ArrayListRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ArrayListValidator implements ConstraintValidator<ArrayListRequired, Object> {

    @Override
    public void initialize(ArrayListRequired arrayListRequired) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        return obj instanceof List;
    }
}
