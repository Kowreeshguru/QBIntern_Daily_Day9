package com.quinbay.wholesaler.controller;

import com.quinbay.wholesaler.api.WholeasalerInventoryInterface;
import com.quinbay.wholesaler.kafka.KafkaPublishingService;
import com.quinbay.wholesaler.modal.FinanceDepartment;
import com.quinbay.wholesaler.modal.WarehouseStock;
import com.quinbay.wholesaler.modal.Wholesaler;
import com.quinbay.wholesaler.modal.WholesalerInventory;
import com.quinbay.wholesaler.service.WholesalerInventoryService;
import com.quinbay.wholesaler.service.WholesalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WholesalerInventoryController {


    @Autowired
    WholesalerInventoryService wholesalerInventoryService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WholesalerService wholesaler;
    @Autowired
    KafkaPublishingService kafkaPublishingService;

    @PostMapping("/addnewProduct")
    public String addProducts(@RequestBody WholesalerInventory inputWholesalerIn)
    {
        return wholesalerInventoryService.addProduct(inputWholesalerIn);
    }

    @GetMapping("/getWholesalerInId")
    public ArrayList<WholesalerInventory> getWholesalerIn(@RequestParam int wholesalerId, @RequestParam int productId)
    {
        return wholesalerInventoryService.findWholesalerInId(wholesalerId,productId);
    }


    @PutMapping("/buyProductwholesaler")
    public String updateProduct(@RequestParam int wholesalerId , @RequestParam int warehouseId,@RequestParam int productId, @RequestParam int quantity ,@RequestParam int AmountYouPay){
        Wholesaler whole=null;
        whole = wholesaler.findWholesalerId(wholesalerId);
        if(whole!=null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<>(headers);
                WarehouseStock availableStock=restTemplate.exchange(
                        "http://localhost:8086/warehouseInventory/getWarehouseId?wareid="+warehouseId+"&prodid="+productId+"", HttpMethod.GET, entity, WarehouseStock.class).getBody();
                if(quantity<=availableStock.stock) {
                    WarehouseStock warePrice=restTemplate.exchange(
                            "http://localhost:8086/warehouseInventory/getWarehouseId?wareid="+warehouseId+"&prodid="+productId+"", HttpMethod.GET, entity, WarehouseStock.class).getBody();
                    System.out.println(warePrice.price+(((warePrice.price)*5)/100));
                    System.out.println(warePrice.price);
                    restTemplate.exchange(
                            "http://localhost:8086/warehouseInventory/sellStockWarehouseIn?wareid=" + warehouseId + "&prodid=" + productId + "&qty=" + quantity + "", HttpMethod.PUT, entity, String.class).getBody();
                    FinanceDepartment fin=new FinanceDepartment(1,wholesalerId,warehouseId,productId,quantity,(quantity*warePrice.price),AmountYouPay);
                    kafkaPublishingService.wholesalerFinanceInfo(fin);
                    return wholesalerInventoryService.updateStock(wholesalerId, warehouseId, productId, quantity,
                            warePrice.price+((warePrice.price*5)/100)
                    );
                }
                else{
                    return "Available stock is "+availableStock.stock+"";
                }
            } catch (Exception e) {
                System.out.println(e);
                return "Enter the correct details";
            }
        }
        else{
            return "Check you wholesaler id";
        }
    }

    @PutMapping("/sellProduct")
    public String sellProduct(@RequestParam int wholesalerId , @RequestParam int warehouseId,@RequestParam int productId, @RequestParam int quantity){
        return wholesalerInventoryService.sellStock(wholesalerId,warehouseId,productId,quantity);
    }



    @DeleteMapping("/deleteProductbyid/{id}")
    public String deleteProductUsingId(@PathVariable(value = "id") Integer id){
        return wholesalerInventoryService.deleteProduct(id);
    }
}
