package com.quinbay.warehouseService.Controller;


import com.quinbay.warehouseService.Model.Warehouse;
import com.quinbay.warehouseService.Service.WarehouseOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseOp warehouse;

    @GetMapping("/getWarehouse")
    public ArrayList<Warehouse> get_ware(){
        return warehouse.disp_warehouse();
    }

    @GetMapping("/getWarehouse/{id}")
    public Warehouse get_ware_byId(@RequestParam int id){
        return warehouse.get_warehouse_byId(id);
    }

    @PostMapping("/addWarehouse")
    public Warehouse add_ware(@RequestBody Warehouse a)
    {
        return warehouse.add_warehouse(a);
    }

    @PutMapping("/updateWarehouseIn")
    public ResponseEntity<String> add_wareIn(@RequestParam int id, @RequestParam String val)
    {
        return warehouse.update_warehouse(id, val);
    }
    //
    @DeleteMapping("/deleteWarehouse/{id}")
    public String det_wae(@RequestParam int id) {
        return warehouse.remove_warehouse(id);

    }
}
