package com.quinbay.warehouseService.Model;

import javax.persistence.*;


@Entity
@Table(name="warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name="wareid",unique =true)
    String wareid;
    @Column(name="name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWareId() {
        return wareid;
    }

    public void setWareId(String wareId) {
        this.wareid = wareId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Warehouse(){}
    Warehouse(int id,String wId,String name){
        this.id=id;
        this.wareid=wId;
        this.name=name;
    }
}
