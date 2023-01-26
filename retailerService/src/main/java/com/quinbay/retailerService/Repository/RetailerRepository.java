package com.quinbay.retailerService.Repository;

import com.quinbay.retailerService.Model.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRepository extends JpaRepository<Retailer, Integer> {
    Retailer findById(int in);
}
