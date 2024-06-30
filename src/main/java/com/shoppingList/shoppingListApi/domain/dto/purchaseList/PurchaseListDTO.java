package com.shoppingList.shoppingListApi.domain.dto.purchaseList;

import com.shoppingList.shoppingListApi.domain.dto.item.ItemDTO;

import java.util.List;

public record PurchaseListDTO(
        Long id,
        String listName,
        List<ItemDTO> items
) {}