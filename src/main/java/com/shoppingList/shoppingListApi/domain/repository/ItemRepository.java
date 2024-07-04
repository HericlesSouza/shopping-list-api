package com.shoppingList.shoppingListApi.domain.repository;

import com.shoppingList.shoppingListApi.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNameAndPurchaseListId(String name, Long purchaseListId);
}
