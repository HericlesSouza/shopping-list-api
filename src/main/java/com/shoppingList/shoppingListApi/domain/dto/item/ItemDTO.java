package com.shoppingList.shoppingListApi.domain.dto.item;

public record ItemDTO(
        Long id,
        String name,
        Integer quantity
) {
}
