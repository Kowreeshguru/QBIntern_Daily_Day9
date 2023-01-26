package com.quinbay.finance.controller;


import com.quinbay.finance.modal.FinanceDepartment;
import com.quinbay.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @PostMapping("/addPaymentDetails")
    public String addPayment(@RequestParam int buyerid,int sellerid,int productid,int quantity,int totalamt,int paidamt)
    {
        return financeService.updateStock(buyerid,sellerid,productid,quantity,totalamt,paidamt);
    }

    @GetMapping("/findallFinance")
    public ArrayList<FinanceDepartment> getAllFinance() {
        return financeService.findAllFinance();
    }

    @GetMapping("/findbyid")
    public ArrayList<FinanceDepartment> getFinanceUsingId(@RequestParam int buyerid,@RequestParam int sellerid,@RequestParam int productid) {
        return financeService.findPaymentByID(buyerid,sellerid,productid);
    }

    @DeleteMapping("/deleteall")
    public void delete() {
        financeService.deleteAllData();
    }

}
