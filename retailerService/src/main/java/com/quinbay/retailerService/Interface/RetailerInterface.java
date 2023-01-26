package com.quinbay.retailerService.Interface;

import com.quinbay.retailerService.Model.Retailer;

import java.util.ArrayList;

public interface RetailerInterface {
    ArrayList<Retailer> disp_retail();
    Retailer add_retail(Retailer retail);
    Retailer get_retail_byId(int id);
    //    void update_producr(int id,int val);
    String remove_retail(int id);
}
