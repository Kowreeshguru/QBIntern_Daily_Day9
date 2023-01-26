package com.quinbay.retailerService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WholesalerInventory {
    int id;
    int wholesalerid;
    int warehouseid;
    int productid;
    int stock;
    int price;
}

