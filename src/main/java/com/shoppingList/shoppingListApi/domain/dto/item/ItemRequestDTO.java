package com.shoppingList.shoppingListApi.domain.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
        @NotBlank(message = "name is required")
        String name,
        @NotNull(message = "quantity is required")
        Integer quantity
) {
}
