package com.shoppingList.shoppingListApi.domain.controller;

import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListDTO;
import com.shoppingList.shoppingListApi.domain.dto.purchaseList.PurchaseListRequestDTO;
import com.shoppingList.shoppingListApi.domain.service.PurchaseListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
}
