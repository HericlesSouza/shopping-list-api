package com.shoppingList.shoppingListApi.domain.dto.purchaseList;

import java.util.List;

import com.shoppingList.shoppingListApi.domain.annotation.ArrayListRequired;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PurchaseListRequestDTO(
        @NotBlank(message = "listName is required")
        @Pattern(regexp = "^[\\p{L} \\-']+$", message = "listName must contain only letters, spaces, hyphens, and apostrophes")
        String listName,
        @ArrayListRequired
        List<@Valid ItemRequestDTO> items
) {
}
