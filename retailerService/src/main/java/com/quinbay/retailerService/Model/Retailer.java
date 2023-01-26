package com.quinbay.retailerService.Model;

import javax.persistence.*;


@Entity
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="retailerid",unique= true)
    public  String retailerid;
    @Column(name="retailername")
    String retailername;
    Retailer(){}


    public String getR_id() {
        return retailerid;
    }

    public void setR_id(String retailerid) {
        this.retailerid = retailerid;
    }

    public String getR_name() {
        return retailername;
    }

    public void setR_name(String r_name) {
        this.retailername = r_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Retailer(int id, String retailerid,String name){
        this.retailerid=retailerid;
        this.id=id;
        this.retailername=name;

    }
}
