package com.quinbay.finance.api;

import com.quinbay.finance.modal.FinanceDepartment;

import java.util.ArrayList;

public interface FinanceInterface {
    //String addFinance(ArrayList<FinanceDepartment> inputFinance);
    //String findPaymentByID(int buyerid,int sellerid,int productid);
    ArrayList<FinanceDepartment> findPaymentByID(int buyerid,int sellerid,int productid);
    //String updateStock(int buyerid ,int sellerid,int productid,int quantity);
    //String updateStock(int buyerid ,int sellerid,int productid,int quantity,int totalamt,int paidamt);
    String updateStock(int buyerid ,int sellerid,int productid,int quantity,int totalamt,int paidamount);
    ArrayList<FinanceDepartment> findAllFinance();
    void deleteAllData();
}
