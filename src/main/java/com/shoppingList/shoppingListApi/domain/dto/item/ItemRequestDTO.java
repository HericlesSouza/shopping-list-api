package com.shoppingList.shoppingListApi.domain.dto.item;

import jakarta.validation.constraints.NotBlank;

public record ItemRequestDTO(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "quantity is required")
        String quantity
) {
}
