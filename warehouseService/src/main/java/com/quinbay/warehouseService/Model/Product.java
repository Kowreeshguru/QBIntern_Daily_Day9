package com.quinbay.warehouseService.Model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name="productid",unique = true)
    public String productid;
    @Column(name="name")
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }

    @Column(name="price")
    public int price;
    @Column(name="gst")
    public int gst;



    public Product(){}
    public Product(int id, String pId, String name, int stock, int price, int gst){
        this.id=id;
        this.productid=pId;
        this.name=name;
        this.price=price;
        this.gst=gst;
    }
}
