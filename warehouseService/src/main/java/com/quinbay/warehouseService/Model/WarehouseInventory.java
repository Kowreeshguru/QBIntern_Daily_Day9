package com.quinbay.warehouseService.Model;


import javax.persistence.*;

@Entity
@Table(name="warehouseinventory")
public class WarehouseInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "warehouseid")
    int warehouseid;
    @Column(name = "productid")
    int productid;
    @Column(name = "stock")
    int stock;
    @Column(name = "price")
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWareId() {
        return warehouseid;
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

    public void setWareId(int wareId) {
        this.warehouseid = wareId;

    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    WarehouseInventory(){}
    public WarehouseInventory(int wareid,int prodid,int stock,int price){
        this.warehouseid=wareid;
        this.productid=prodid;
        this.stock=stock;
        this.price=price;
    }


}
