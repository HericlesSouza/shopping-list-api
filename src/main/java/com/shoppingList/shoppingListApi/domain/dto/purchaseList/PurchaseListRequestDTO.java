package com.shoppingList.shoppingListApi.domain.dto.purchaseList;

import java.util.List;

import com.shoppingList.shoppingListApi.domain.annotation.ArrayListRequired;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PurchaseListRequestDTO(
        @NotBlank(message = "listName is required")
        String listName,
        @ArrayListRequired
        List<@Valid ItemRequestDTO> items
) {
}
