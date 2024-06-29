package com.shoppingList.shoppingListApi.domain.dto.purchaseList;

import java.util.List;

import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseListRequestDTO(
        @NotBlank(message = "listName is required")
        String listName,
        @NotNull(message = "items are required")
        List<@Valid ItemRequestDTO> items
) {
}
