package com.quinbay.warehouseService.Interface;

import com.quinbay.warehouseService.Model.Warehouse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WarehouseInterface {
    ArrayList<Warehouse> disp_warehouse();
    Warehouse add_warehouse(Warehouse warehouse);
    Warehouse get_warehouse_byId(int id);
    ResponseEntity<String> update_warehouse(int id, String val);
    String remove_warehouse(int id);
}
