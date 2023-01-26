package com.quinbay.warehouseService.Service;

import com.quinbay.warehouseService.Interface.WarehouseInventoryInterface;
import com.quinbay.warehouseService.Model.WarehouseInventory;
import com.quinbay.warehouseService.Repository.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WarehouseInventoryOp implements WarehouseInventoryInterface {
    @Autowired
    WarehouseInventoryRepository wareIn;


    //    public static ArrayList<warehouse_inventory> warehouseIn = new ArrayList<>();
    @Override
    public ArrayList<WarehouseInventory> disp_warehouseIn() {
        return (ArrayList<WarehouseInventory>) wareIn.findAll();
    }
    @Cacheable(value="warehouseinventoryList")
    public WarehouseInventory add_warehouseIn(WarehouseInventory add_wareIn) {
        return wareIn.save(add_wareIn);
    }
//    @Cacheable(value="warehouseinventory",key="{#wareid,#prodid}")
    public WarehouseInventory get_warehouseIn_byId(int wareid,int prodid){
        try {
//            return wareIn.findById(wareInId);
            return wareIn.findByWarehouseidAndProductid(wareid,prodid);
        }catch (Exception e){
            return null;
        }
    }
    public ResponseEntity<String> update_warehouseIn(int wareid,int prodid, int qty,int price){
        WarehouseInventory ware=null;
        ware = wareIn.findByWarehouseidAndProductid(wareid,prodid);
        if(ware != null) {
            try {
                ware.setStock(ware.getStock() + qty);
                ware.setPrice(price);
                wareIn.save(ware);
                return new ResponseEntity("Successfully update", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity("Not updated, Id not founded", HttpStatus.BAD_REQUEST);
            }
        }
        else{
            try {
                ware = new WarehouseInventory(wareid,prodid, qty, price);
                wareIn.save(ware);
                return new ResponseEntity("Successfully update", HttpStatus.OK);
            }catch(Exception e){
                return new ResponseEntity("Not updated, Id not founded", HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<String> sell_warehouseIn(int wareid,int prodid, int val){
        try {
            WarehouseInventory warehouseInventory = wareIn.findByWarehouseidAndProductid(wareid,prodid);
            warehouseInventory.setStock(warehouseInventory.getStock()-val);
            wareIn.save(warehouseInventory);
            return new ResponseEntity("Successfully update", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated ,Id not found", HttpStatus.BAD_REQUEST);
        }
    }

    public String remove_warehouseIn(int id){
        try{
            wareIn.deleteById(id);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }

    }
}

