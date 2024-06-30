package com.shoppingList.shoppingListApi.domain.controller;

import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListRequestDTO;
import com.shoppingList.shoppingListApi.domain.service.PurchaseListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
