package com.quinbay.wholesaler.api;

import com.quinbay.wholesaler.modal.Wholesaler;
import com.quinbay.wholesaler.modal.WholesalerInventory;

import java.util.ArrayList;
import java.util.List;

public interface WholeasalerInventoryInterface {

    String addProduct(WholesalerInventory inputWholesalerInventory);
    ArrayList<WholesalerInventory> findWholesalerInId(int wholeid, int prodid);
    String deleteProduct (int id);
    String updateStock(int whoid ,int warid,int pid,int quantity,int inprice);
    String sellStock(int whoid ,int warid,int pid,int quantity);
}
