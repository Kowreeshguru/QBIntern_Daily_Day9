package com.quinbay.retailerService.Service;

import com.quinbay.retailerService.Interface.RetailerInterface;
import com.quinbay.retailerService.Repository.RetailerRepository;
import com.quinbay.retailerService.Model.Retailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;



@Service
public class RetailerOp implements RetailerInterface {
    @Autowired
    RetailerRepository retailrepo;

    @Override
    @Cacheable(value = "retailerList")
    public ArrayList<Retailer> disp_retail() {
        return (ArrayList<Retailer>) retailrepo.findAll();
    }

    public Retailer add_retail(Retailer add_retail) {
        return retailrepo.save(add_retail);
    }

    @Cacheable(value="retail", key="{}")
    public Retailer get_retail_byId(int retialId){
        try {
            return retailrepo.findById(retialId);
        }catch (Exception e){
            return null;
        }

    }
    public ResponseEntity<String> update_retail(int id, String val){
        try {
            Retailer retail = retailrepo.findById(id);
            retail.setR_name(val);
            retailrepo.save(retail);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }

    }

    public String remove_retail(int id){
        try{
            retailrepo.deleteById(id);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }

    }
}
