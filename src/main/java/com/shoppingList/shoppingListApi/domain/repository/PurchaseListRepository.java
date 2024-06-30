package com.shoppingList.shoppingListApi.domain.repository;

import com.shoppingList.shoppingListApi.domain.model.PurchaseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long> {
}
