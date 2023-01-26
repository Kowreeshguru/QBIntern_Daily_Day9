package com.quinbay.wholesaler.api;

import com.quinbay.wholesaler.modal.FinanceDepartment;
import com.quinbay.wholesaler.modal.Wholesaler;

import java.util.ArrayList;

public interface WholesalerInterface {

    ArrayList<Wholesaler> findAllWholesaler();
    Wholesaler findWholesalerId(int wid);
    String addWholesaler(ArrayList<Wholesaler> inputWholesaler);
    void deleteAllData();
    String deleteWholesaler(int id);
//    ResposeEntity<string> updateWholesaler(int id, String wref);
    void buyProduct(FinanceDepartment finance);

}
