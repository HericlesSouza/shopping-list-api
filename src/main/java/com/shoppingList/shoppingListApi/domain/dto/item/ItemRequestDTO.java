package com.shoppingList.shoppingListApi.domain.dto.item;

import jakarta.validation.constraints.*;

public record ItemRequestDTO(
        @NotBlank(message = "name is required")
        String name,
        @NotNull(message = "quantity is required")
        @Positive(message = "quantity must be greater than 0")
        Integer quantity
) {
}
