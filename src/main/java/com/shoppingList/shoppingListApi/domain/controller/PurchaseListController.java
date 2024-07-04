package com.shoppingList.shoppingListApi.domain.controller;

import com.shoppingList.shoppingListApi.domain.dto.item.ItemDTO;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemRequestDTO;
import com.shoppingList.shoppingListApi.domain.dto.item.ItemValidationGroups;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListRequestDTO;
import com.shoppingList.shoppingListApi.domain.service.PurchaseListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("purchaseList")
public class PurchaseListController {
    @Autowired
    PurchaseListService purchaseListService;

    @PostMapping
    public ResponseEntity<PurchaseListDTO> create(@Valid @RequestBody PurchaseListRequestDTO data) {
        PurchaseListDTO createdList = purchaseListService.create(data);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseListDTO>> listAll() {
        List<PurchaseListDTO> purchaseList = purchaseListService.findAll();
        return new ResponseEntity<>(purchaseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseListDTO> listById(@PathVariable Long id) {
        PurchaseListDTO purchaseList = purchaseListService.findById(id);
        return new ResponseEntity<>(purchaseList, HttpStatus.OK);
    }

    @PatchMapping("/{id}/{itemName}")
    public ResponseEntity<ItemDTO> updateItemByName(
            @PathVariable Long id,
            @PathVariable String itemName,
            @Validated(ItemValidationGroups.Update.class) @RequestBody ItemRequestDTO data
    ) {
        ItemDTO purchaseList = purchaseListService.updateItemByName(id, itemName, data);
        return new ResponseEntity<>(purchaseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{itemName}")
    public ResponseEntity<Void> deleteItemByName(
            @PathVariable Long id,
            @PathVariable String itemName
    ) {
        purchaseListService.deleteItemByName(id, itemName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ) {
        purchaseListService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
