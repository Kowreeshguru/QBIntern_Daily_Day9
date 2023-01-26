package com.quinbay.wholesaler.modal;

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
public class WholesalerInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "wholesalerid")
    int wholesalerid;
    @Column(name = "warehouseid")
    int warehouseid;
    @Column(name = "productid")
    int productid;
    @Column(name = "stock")
    int stock;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "price")
    int price;


//    public WholesalerInventory(){}
    public WholesalerInventory(int wholesalerid, int warehouseid, int productid, int stock, int price) {
        this.wholesalerid = wholesalerid;
        this.warehouseid = warehouseid;
        this.productid = productid;
        this.stock = stock;
        this.price = price;
    }

}
