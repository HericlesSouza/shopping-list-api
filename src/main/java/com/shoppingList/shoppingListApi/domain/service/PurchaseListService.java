package com.shoppingList.shoppingListApi.domain.service;

import com.shoppingList.shoppingListApi.domain.dto.item.ItemDTO;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import com.shoppingList.shoppingListApi.domain.dto.pagedResponse.PagedResponseDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListRequestDTO;
import com.shoppingList.shoppingListApi.domain.exception.ResourceNotFoundException;
import com.shoppingList.shoppingListApi.domain.model.Item;
import com.shoppingList.shoppingListApi.domain.model.PurchaseList;
import com.shoppingList.shoppingListApi.domain.repository.ItemRepository;
import com.shoppingList.shoppingListApi.domain.repository.PurchaseListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseListService {
    @Autowired
    private PurchaseListRepository purchaseListRepository;
    @Autowired
    private ItemRepository itemRepository;

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

        return this.purchaseListToDTO(savedList);
    }

    public PagedResponseDTO<PurchaseListDTO> findAll(int page, int size, String sort, String order) {
        Sort.Direction direction = Sort.Direction.fromString(order);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<PurchaseList> purchaseListPage = purchaseListRepository.findAll(pageable);

        List<PurchaseListDTO> purchaseListDTOs = purchaseListPage.stream()
                .map(this::purchaseListToDTO)
                .toList();

        String previousPage = purchaseListPage.hasPrevious() ?
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .replaceQueryParam("page", page - 1)
                        .replaceQueryParam("size", size)
                        .replaceQueryParam("sort", sort)
                        .replaceQueryParam("order", order)
                        .toUriString() : null;

        String nextPage = purchaseListPage.hasNext() ?
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .replaceQueryParam("page", page + 1)
                        .replaceQueryParam("size", size)
                        .replaceQueryParam("sort", sort)
                        .replaceQueryParam("order", order)
                        .toUriString() : null;

        return new PagedResponseDTO<>(
                previousPage,
                nextPage,
                page,
                size,
                purchaseListPage.getTotalElements(),
                purchaseListPage.getTotalPages(),
                purchaseListDTOs.size(),
                purchaseListDTOs
        );
    }

    public PurchaseListDTO findById(Long id) {
        return purchaseListRepository.findById(id).map(this::purchaseListToDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public ItemDTO updateItemByName(Long purchaseListId, String itemName, ItemRequestDTO itemRequestDTO) {
        Item item = itemRepository.findByNameAndPurchaseListId(itemName, purchaseListId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item '" + itemName + "' not found in the purchase list with ID: " + purchaseListId
                ));

        Optional.ofNullable(itemRequestDTO.name()).ifPresent(item::setName);
        Optional.ofNullable(itemRequestDTO.quantity()).ifPresent(item::setQuantity);

        itemRepository.save(item);
        return itemToDto(item);
    }

    public void deleteItemByName(Long purchaseListId, String itemName) {
        Item item = itemRepository.findByNameAndPurchaseListId(itemName, purchaseListId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item '" + itemName + "' not found in the purchase list with ID: " + purchaseListId)
                );

        itemRepository.delete(item);
    }

    public void deleteById(Long purchaseListId) {
        PurchaseList purchaseList = purchaseListRepository.findById(purchaseListId)
                .orElseThrow(ResourceNotFoundException::new);
        purchaseListRepository.delete(purchaseList);
    }

    private PurchaseListDTO purchaseListToDTO(PurchaseList purchaseList) {
        List<ItemDTO> itemDTOs = purchaseList.getItems().stream()
                .map(item -> new ItemDTO(item.getId(), item.getName(), item.getQuantity()))
                .toList();
        return new PurchaseListDTO(purchaseList.getId(), purchaseList.getListName(), itemDTOs);
    }

    private ItemDTO itemToDto(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getQuantity());
    }
}
