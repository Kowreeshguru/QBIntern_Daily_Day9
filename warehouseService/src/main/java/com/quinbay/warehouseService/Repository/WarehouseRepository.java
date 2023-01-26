package com.quinbay.warehouseService.Repository;


import com.quinbay.warehouseService.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findById(int in);
}
