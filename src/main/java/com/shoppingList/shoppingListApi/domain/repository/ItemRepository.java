package com.shoppingList.shoppingListApi.domain.repository;

import com.shoppingList.shoppingListApi.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
