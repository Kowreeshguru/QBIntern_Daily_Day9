package com.quinbay.warehouseService.Service;

import com.quinbay.warehouseService.Interface.ProductInterface;
import com.quinbay.warehouseService.Model.Product;
import com.quinbay.warehouseService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@CacheConfig(cacheNames = "Products")
public class ProductOp implements ProductInterface {

    @Autowired
    ProductRepository proRepo;
    @Autowired
//    KafkaPublishingService kafkaPublishingService;

    //    public static ArrayList<Product> product = new ArrayList<>();
    @Override
    @Cacheable(value="productList")
    public List<Product> disp_product() {
//        System.out.println("Calling method disp_product...!");
//        Product prod=new Product(101,"Product1",1000,45000,18);
//        ArrayList<Product> product=new ArrayList<>();
//        product.add(prod);
//        return product;
        return (ArrayList<Product>) proRepo.findAll();
    }
    @Override
    public Product add_product(Product add_prod) {
        return proRepo.save(add_prod);
    }

    @Override
    @Cacheable(value="Productbyid", key="#productId")
    public int get_product_byId(int productId){
//        System.out.println("Calling method get_product_byId...!");
        Product product=proRepo.findById(productId);
        try {
            return proRepo.findById(productId).getPrice();
        }catch(Exception e){
            return 0;
        }
    }

    //    @CachePut(value="Productupdate",key="#productId")
    public ResponseEntity<String> update_Product(int productId, int price){
        try {
            Product product = proRepo.findById(productId);
//            product.setName(name);
            product.setPrice(price);
            proRepo.save(product);
            return new ResponseEntity("Successfully update", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Not updated, Id not found", HttpStatus.BAD_REQUEST);
        }
    }


    @Override
//    @CacheEvict(value="Productdelete",key="#productId")
    public String remove_product(int productId){
        try{
            proRepo.deleteById(productId);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }

    }
}
