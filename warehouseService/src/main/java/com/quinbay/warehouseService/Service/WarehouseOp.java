package com.quinbay.warehouseService.Service;


import com.quinbay.warehouseService.Interface.WarehouseInterface;
import com.quinbay.warehouseService.Model.Warehouse;
import com.quinbay.warehouseService.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WarehouseOp implements WarehouseInterface {
    @Autowired
    WarehouseRepository warerepo;

    //    public static ArrayList<Product> product = new ArrayList<>();
    @Override
    @Cacheable(value = "warehouselist")
    public ArrayList<Warehouse> disp_warehouse() {
        return (ArrayList<Warehouse>) warerepo.findAll();
    }

    @Override
    public Warehouse add_warehouse(Warehouse add_ware) {
        return warerepo.save(add_ware);
    }

    @Override
    @Cacheable(value = "warehouse",key="#wareId")
    public Warehouse get_warehouse_byId(int wareId){
        try {
            return warerepo.findById(wareId);
        }catch (Exception e){
            return null;
        }
    }
    public ResponseEntity<String> update_warehouse(int id, String val){
        try {
            Warehouse warehouse = warerepo.findById(id);
            warehouse.setName(val);
            warerepo.save(warehouse);
            return new ResponseEntity("Successfully update", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, Id not found", HttpStatus.OK);
        }
    }
    @Override
    public String remove_warehouse(int id){
        try{
            warerepo.deleteById(id);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }

    }

}
