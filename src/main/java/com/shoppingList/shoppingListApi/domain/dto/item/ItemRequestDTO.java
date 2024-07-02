package com.shoppingList.shoppingListApi.domain.dto.item;

import com.shoppingList.shoppingListApi.domain.annotation.OptionalNotBlank;
import com.shoppingList.shoppingListApi.domain.annotation.OptionalPositive;
import jakarta.validation.constraints.*;

public record ItemRequestDTO(
        @NotBlank(message = "name is required")
        @OptionalNotBlank(message = "name must not be blank", groups = ItemValidationGroups.Update.class)
        String name,

        @NotNull(message = "quantity is required")
        @Positive(message = "quantity must be greater than 0")
        @OptionalPositive(message = "quantity must be greater than 0", groups = ItemValidationGroups.Update.class)
        Integer quantity
) {
}