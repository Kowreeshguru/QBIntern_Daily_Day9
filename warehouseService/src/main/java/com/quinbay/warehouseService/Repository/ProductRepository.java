package com.quinbay.warehouseService.Repository;

import com.quinbay.warehouseService.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //    void updatePrice(int id,int price);
    Product findById(int in);
}