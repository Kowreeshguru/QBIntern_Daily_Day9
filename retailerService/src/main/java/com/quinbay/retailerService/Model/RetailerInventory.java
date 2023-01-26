package com.quinbay.retailerService.Model;

import javax.persistence.*;


@Entity
public class RetailerInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="retailerid")
    int retailerid;
    @Column(name="wholesalerid")
    int wholesalerid;
    @Column(name="productid")
    int productid;
    @Column(name="stock")
    int stock;
    @Column(name="price")
    int price;

    public RetailerInventory(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getR_id() {
        return retailerid;
    }

    public void setR_id(int r_id) {
        this.retailerid = r_id;
    }

    public int getW_id() {
        return wholesalerid;
    }

    public void setW_id(int w_id) {
        this.wholesalerid = w_id;
    }

    public int getP_id() {
        return productid;
    }

    public void setP_id(int p_id) {
        this.productid = p_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public RetailerInventory(int r_id,int w_id,int p_id,int stock,int price){
        this.retailerid=r_id;
        this.wholesalerid=w_id;
        this.productid=p_id;
        this.stock=stock;
        this.price=price;
    }
}
