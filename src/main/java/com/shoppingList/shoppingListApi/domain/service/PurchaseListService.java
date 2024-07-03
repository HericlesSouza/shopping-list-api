package com.shoppingList.shoppingListApi.domain.service;

import com.shoppingList.shoppingListApi.domain.dto.item.ItemDTO;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListRequestDTO;
import com.shoppingList.shoppingListApi.domain.exception.ResourceNotFoundException;
import com.shoppingList.shoppingListApi.domain.model.Item;
import com.shoppingList.shoppingListApi.domain.model.PurchaseList;
import com.shoppingList.shoppingListApi.domain.repository.PurchaseListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseListService {
    @Autowired
    private PurchaseListRepository purchaseListRepository;

    @Transactional
    public PurchaseListDTO create(PurchaseListRequestDTO data) {
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.setListName(data.listName());
        List<Item> items = data.items().stream().map(itemDTO ->
                        Item.builder()
                                .name(itemDTO.name())
                                .quantity(itemDTO.quantity())
                                .purchaseList(purchaseList)
                                .build())
                .toList();
        purchaseList.setItems(items);
        PurchaseList savedList = purchaseListRepository.save(purchaseList);

        return this.mapToDTO(savedList);
    }

    public List<PurchaseListDTO> findAll() {
        return purchaseListRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public PurchaseListDTO findById(Long id) {
        return purchaseListRepository.findById(id).map(this::mapToDTO).orElseThrow(ResourceNotFoundException::new);
    }

    public PurchaseListDTO updateItem(Long id, String itemName, ItemRequestDTO itemRequestDTO) {
        PurchaseList purchaseList = purchaseListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("purchase list not found"));

        Item item = purchaseList.getItems().stream()
                .filter(itemFilter -> itemFilter.getName().equals(itemName))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("item '" + itemName + "' not found"));

        Optional.ofNullable(itemRequestDTO.name()).ifPresent(item::setName);
        Optional.ofNullable(itemRequestDTO.quantity()).ifPresent(item::setQuantity);

        purchaseListRepository.save(purchaseList);
        return mapToDTO(purchaseList);
    }

    private PurchaseListDTO mapToDTO(PurchaseList purchaseList) {
        List<ItemDTO> itemDTOs = purchaseList.getItems().stream()
                .map(item -> new ItemDTO(item.getId(), item.getName(), item.getQuantity()))
                .toList();
        return new PurchaseListDTO(purchaseList.getId(), purchaseList.getListName(), itemDTOs);
    }
}
