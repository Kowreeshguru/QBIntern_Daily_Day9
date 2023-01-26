package com.quinbay.warehouseService.Controller;

import com.quinbay.warehouseService.Model.Product;
import com.quinbay.warehouseService.Service.ProductOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductOp product;

    @GetMapping("/getProduct")
    public List<Product> get_prod(){
        return product.disp_product();
    }

    //    @Cacheable(value="Product", key="#productId", unless="#result.stock > 100000")
    @GetMapping("/getProductById")
    public int get_prod_byId(@RequestParam int productId){
        return product.get_product_byId(productId);
    }

    @PostMapping("/addProduct")
    public Product add_prod(@RequestBody Product a)
    {
        return product.add_product(a);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> add_wareIn(@RequestParam int id, @RequestParam int price)
    {
        return product.update_Product(id, price);
    }
    //
    @DeleteMapping("/deleteProductById")
    public String det_prod(@RequestParam int id) {
        return product.remove_product(id);

    }

}