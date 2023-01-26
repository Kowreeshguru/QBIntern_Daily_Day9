package com.quinbay.retailerService.Interface;

import com.quinbay.retailerService.Model.RetailerInventory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface RetailerInventoryInterface {
    ArrayList<RetailerInventory> disp_retailIn();
    RetailerInventory add_retailIn(RetailerInventory retailIn);
    RetailerInventory get_retailIn_byId(int retailid,int wholeid,int prodid);
    //    void update_producr(int id,int val);
    String remove_retailIn(int id);
}
