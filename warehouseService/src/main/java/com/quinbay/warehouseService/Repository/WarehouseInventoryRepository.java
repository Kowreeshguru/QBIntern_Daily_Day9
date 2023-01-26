package com.quinbay.warehouseService.Repository;

import com.quinbay.warehouseService.Model.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Integer> {
    WarehouseInventory findById(int in);
    WarehouseInventory findByWarehouseidAndProductid(int ware_id,int p_id);
}