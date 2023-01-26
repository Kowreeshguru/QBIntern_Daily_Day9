package com.quinbay.warehouseService.Controller;


import com.quinbay.warehouseService.Model.Warehouse;
import com.quinbay.warehouseService.Model.WarehouseInventory;
import com.quinbay.warehouseService.Service.WarehouseInventoryOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/warehouseInventory")
public class warehouseInventoryController {
    @Autowired
    WarehouseInventoryOp warehouseInventory;

    @GetMapping("/getWarehouseIn")
    public ArrayList<WarehouseInventory> get_ware(){
        return warehouseInventory.disp_warehouseIn();
    }

    @GetMapping("/getWarehouseId")
    public WarehouseInventory get_warehouseIn_byId(@RequestParam int wareid, @RequestParam int prodid){
        return warehouseInventory.get_warehouseIn_byId(wareid,prodid);
    }
//
//    @PostMapping("/addWarehouseIn")
//    public WarehouseInventory add_wareIn(@RequestBody WarehouseInventory a)
//    {
//        return warehouseInventory.add_warehouseIn(a);
//    }

    @PutMapping("/addStockWarehouseIn")
    public ResponseEntity<String> add_wareIn(@RequestParam int wareid,@RequestParam int prodid, @RequestParam int qty,@RequestParam int setYourPricr)
    {
        return warehouseInventory.update_warehouseIn(wareid,prodid, qty,setYourPricr);
    }
    @PutMapping("/sellStockWarehouseIn")
    public ResponseEntity<String> sell_wareIn(@RequestParam int wareid,@RequestParam int prodid, @RequestParam int qty)
    {
        System.out.println("in ware update");
        return warehouseInventory.sell_warehouseIn(wareid,prodid, qty);
    }
    //
//    @DeleteMapping("/deleteWarehouseIn/{id}")
//    public String det_ware(@RequestParam int wareid,@RequestParam int prodid) {
////        WarehouseInventory warehouseInventory=warehouseInventory.get_warehouseIn_byId(wareid,prodid).id;
////        return warehouseInventory.remove_warehouseIn(warehouseInventory.get_warehouseIn_byId(wareid,prodid).getId());
//            return null;
//    }
}
