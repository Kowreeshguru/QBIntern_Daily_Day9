package com.quinbay.retailerService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceDepartment {
    int id;
    int buyerid;
    int sellerid;
    int productid;
    int quantity;
    int totalamount;
    int balancepay;
//    public FinanceDepartment(){}

//    public FinanceDepartment(int buyerid, int sellerid, int productid, int payamount, int totalamount, int quantity) {
//        this.buyerid = buyerid;
//        this.sellerid = sellerid;
//        this.productid = productid;
//        this.payamount = payamount;
//        this.totalamount = totalamount;
//        this.quantity = quantity;
//    }


}
