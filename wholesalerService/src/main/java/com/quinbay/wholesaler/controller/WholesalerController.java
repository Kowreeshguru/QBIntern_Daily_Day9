package com.quinbay.wholesaler.controller;


import com.quinbay.wholesaler.modal.FinanceDepartment;
import com.quinbay.wholesaler.modal.Wholesaler;
import com.quinbay.wholesaler.service.WholesalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class WholesalerController {

    @Autowired
    WholesalerService wholesalerService;

    @PostMapping("/addWholesaler")
    public String postData(@RequestBody ArrayList<Wholesaler> inputWholesaler)
    {
        return wholesalerService.addWholesaler(inputWholesaler);
    }

    @GetMapping("/findallWholesaler")
    public ArrayList<Wholesaler> getAllWholesaler() {
        return wholesalerService.findAllWholesaler();
    }

    @GetMapping("/findbyId")
    public Wholesaler returnWholesaler(@RequestParam int wid) {
        return wholesalerService.findWholesalerId(wid);
    }

    @PutMapping("/updateWholesaler")
    public ResponseEntity<String> updateWholesaler(@RequestParam int id, @RequestParam String wref){
        return wholesalerService.updateWholesaler(id,wref);
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteWholesalerUsingId(@PathVariable(value = "id") Integer id){
        return wholesalerService.deleteWholesaler(id);
    }

    @DeleteMapping("/deleteall")
    public void delete() {
        wholesalerService.deleteAllData();
    }


}
