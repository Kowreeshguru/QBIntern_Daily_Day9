package com.quinbay.retailerService.Service;

import com.quinbay.retailerService.Interface.RetailerInventoryInterface;
import com.quinbay.retailerService.Model.RetailerInventory;
import com.quinbay.retailerService.Repository.RetailerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RetailerInventoryOp implements RetailerInventoryInterface {
    @Autowired
    RetailerInventoryRepository retailInrepo;

    //    public static ArrayList<Product> product = new ArrayList<>();
    @Override
    @Cacheable(value = "retailerstocklist")
    public ArrayList<RetailerInventory> disp_retailIn() {
        return (ArrayList<RetailerInventory>) retailInrepo.findAll();
    }

    public RetailerInventory add_retailIn(RetailerInventory add_retailIn) {
        return retailInrepo.save(add_retailIn);
    }

//    @Cacheable(value = "retailerstock", key="{#retialid,#wholeid,#prodid}")
    public RetailerInventory get_retailIn_byId(int retialid,int wholeid,int prodid){
        try{
            return retailInrepo.findByRetaileridAndWholesaleridAndProductid(retialid,wholeid,prodid);
        }catch (Exception e){
            return null;
        }
    }
    public String update_retailIn(int retailerid,int wareId,int prodId, int qty,int price){

        RetailerInventory retailIn=null;
        retailIn = retailInrepo.findByRetaileridAndWholesaleridAndProductid(retailerid,wareId,prodId);
        if(retailIn != null) {
            try {
                retailIn.setStock(retailIn.getStock() + qty);
                retailIn.setPrice(price);
                retailInrepo.save(retailIn);
                return "Successfully update";
            } catch (Exception e) {
                return "Not updated, Id not founded";
            }
        }
        else{
            try {
                retailIn = new RetailerInventory(retailerid, wareId, prodId, qty, price);
                retailInrepo.save(retailIn);
                return "Successfully update";
            }catch(Exception e){
                return "Not updated, Id not founded";
            }
        }
    }

    public String remove_retailIn(int id){
        try{
            retailInrepo.deleteById(id);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }

    }
}
