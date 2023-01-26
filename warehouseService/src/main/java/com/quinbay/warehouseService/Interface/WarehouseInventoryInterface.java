package com.quinbay.warehouseService.Interface;

import com.quinbay.warehouseService.Model.WarehouseInventory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WarehouseInventoryInterface {
    ArrayList<WarehouseInventory> disp_warehouseIn();
    WarehouseInventory add_warehouseIn(WarehouseInventory warehouseIn);
    WarehouseInventory get_warehouseIn_byId(int wareid,int prodid);
    String remove_warehouseIn(int id);
}
