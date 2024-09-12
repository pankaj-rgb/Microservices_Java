package com.pankajproduct.inventory_service.repository;

import com.pankajproduct.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySkuCode(String skucode);
}
