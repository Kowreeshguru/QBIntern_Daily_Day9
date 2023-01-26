package com.quinbay.retailerService.Repository;

import com.quinbay.retailerService.Model.RetailerInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RetailerInventoryRepository extends JpaRepository<RetailerInventory, Integer> {
    RetailerInventory findByRetaileridAndWholesaleridAndProductid(int retailid,int wholeid,int prodid);
    RetailerInventory findById(int id);
}