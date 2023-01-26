package com.quinbay.warehouseService.Interface;

import com.quinbay.warehouseService.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductInterface {
    List<Product> disp_product();
    Product add_product(Product product);
    int get_product_byId(int id);
    //    void update_producr(int id,int val);
    String remove_product(int id);
}
