package com.quinbay.finance.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;
    @Column(name = "buyerid")
    int buyerid;
    @Column(name = "sellerid")
    int sellerid;
    @Column(name = "productid")
    int productid;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "totalamount")
    int totalamount;
    @Column(name = "balancepay")
    int balancepay;

    public FinanceDepartment(int buyerid, int sellerid, int productid, int quantity ,int totalamount) {
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.productid = productid;
        this.totalamount = totalamount;
        this.quantity = quantity;
    }

    public FinanceDepartment(int buyerid, int sellerid, int productid, int quantity ,int totalamount, int balancepay) {
            this.buyerid = buyerid;
            this.sellerid = sellerid;
            this.productid = productid;
            this.totalamount = totalamount;
            this.balancepay = balancepay;
            this.quantity = quantity;
        }
}
