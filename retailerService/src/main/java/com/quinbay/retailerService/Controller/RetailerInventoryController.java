package com.quinbay.retailerService.Controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.retailerService.Model.FinanceDepartment;
import com.quinbay.retailerService.Model.Retailer;
import com.quinbay.retailerService.Model.RetailerInventory;
import com.quinbay.retailerService.Model.WholesalerInventory;
import com.quinbay.retailerService.Service.RetailerInventoryOp;
import com.quinbay.retailerService.Service.RetailerOp;
import com.quinbay.retailerService.kafka.KafkaPublishingService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/retailerIn")
public class RetailerInventoryController {
    @Autowired
    RetailerInventoryOp retailerIn;
    @Autowired
    RetailerOp retailer;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KafkaPublishingService kafkaPublishingService;


    @GetMapping("/getRetailerIn")
    public ArrayList<RetailerInventory> get_retailIn(){
        return retailerIn.disp_retailIn();
    }

    @GetMapping("/getRetailInbyId")
    public RetailerInventory get_retailIn_byId(@RequestParam int retailid,@RequestParam int wholeid,@RequestParam int prodid){
        return retailerIn.get_retailIn_byId(retailid,wholeid,prodid);
    }

    @PostMapping("/addRetailIn")
    public RetailerInventory add_retailerIn(@RequestBody RetailerInventory a)
    {
        return retailerIn.add_retailIn(a);
    }

    @PutMapping("/buyProductsRetailer")
    public String add_RetailIn(@RequestParam int id,@RequestParam int wholeId,@RequestParam int prodId, @RequestParam int quantity,@RequestParam int Amountpayed) {
        Retailer retailercheck = null;
        retailercheck = retailer.get_retail_byId(id);
        if (retailercheck != null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<>(headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode wholesalerstock = restTemplate.exchange(
                        "http://localhost:8082/getWholesalerInId?wholesalerId=" + wholeId + "&productId=" + prodId + "", HttpMethod.GET, entity, JsonNode.class).getBody();
                List<WholesalerInventory> wholesalerstockList = mapper.convertValue(
                        wholesalerstock,
                        new TypeReference<List<WholesalerInventory>>(){}
                );
                int availableStock = 0;
//                for (WholesalerInventory wholeIter : wholesalerstockList) {
//                    System.out.println("test2");
//                    System.out.println(wholeIter.getStock());
//                    availableStock = availableStock + wholeIter.getStock();
//                    System.out.println(availableStock);
//                }
                for(int j=0;j<wholesalerstockList.size();j++){
                    availableStock = availableStock + wholesalerstockList.get(j).getStock();
                }

                System.out.println(quantity);

                if (quantity <= availableStock) {
                    System.out.println("Test0");
                    int i=0;
                    while (quantity != 0) {

                        System.out.println(quantity);
                        if (wholesalerstockList.get(i).getStock() < quantity) {
                            System.out.println("Test2");
                            quantity = quantity - wholesalerstockList.get(i).getStock();
                            System.out.println("http://localhost:8082/sellProduct?wholesalerId=" + wholesalerstockList.get(i).getWholesalerid() + "&warehouseId=" + wholesalerstockList.get(i).getWarehouseid() + "&productId=" + wholesalerstockList.get(i).getProductid() + "&quantity=" + wholesalerstockList.get(i).getStock() + "");
                            String str=restTemplate.exchange(
                                    "http://localhost:8082/sellProduct?wholesalerId=" + wholesalerstockList.get(i).getWholesalerid() + "&warehouseId=" + wholesalerstockList.get(i).getWarehouseid() + "&productId=" + wholesalerstockList.get(i).getProductid() + "&quantity=" + wholesalerstockList.get(i).getStock() + "", HttpMethod.PUT, entity, String.class).getBody();
                            FinanceDepartment fin = new FinanceDepartment(1, id, wholeId, prodId, wholesalerstockList.get(i).getStock(), (wholesalerstockList.get(i).getStock() * wholesalerstockList.get(i).getPrice()), Amountpayed);
                            System.out.println("Test3");
                            kafkaPublishingService.wholesalerFinanceInfo(fin);
                            retailerIn.update_retailIn(id, wholeId, prodId, wholesalerstockList.get(i).getStock(), wholesalerstockList.get(i).getPrice() + ((wholesalerstockList.get(i).getPrice() * 5) / 100));
                            i++;
                            return "Updated successfully";
                        } else {
                            System.out.println("http://localhost:8082/sellProduct?wholesalerId=" + wholesalerstockList.get(i).getWholesalerid() + "&warehouseId=" + wholesalerstockList.get(i).getWarehouseid() + "&productId=" + wholesalerstockList.get(i).getProductid() + "&quantity=" + quantity + "");
                            String str=restTemplate.exchange(
                                    "http://localhost:8082/sellProduct?wholesalerId=" + wholesalerstockList.get(i).getWholesalerid() + "&warehouseId=" + wholesalerstockList.get(i).getWarehouseid() + "&productId=" + wholesalerstockList.get(i).getProductid() + "&quantity=" + quantity + "", HttpMethod.PUT, entity, String.class).getBody();
                            FinanceDepartment fin = new FinanceDepartment(1, id, wholeId, prodId, wholesalerstockList.get(i).getStock(), (wholesalerstockList.get(i).getStock() * wholesalerstockList.get(i).getPrice()), Amountpayed);
                            kafkaPublishingService.wholesalerFinanceInfo(fin);
                            retailerIn.update_retailIn(id, wholeId, prodId, quantity, wholesalerstockList.get(i).getPrice() + ((wholesalerstockList.get(i).getPrice() * 5) / 100));
                            quantity = 0;
                            i++;
                            return "Updated successfully";
                        }
                    }
                } else {
                    return "Available stock is " + availableStock + "";
                }
            } catch (Exception e) {
                System.out.println(e);
                return "Enter the correct details";
            }
        } else {
            return "Check your retailer id";
        }
        return null;
    }
    //
    @DeleteMapping("/deleteRetailIn/{id}")
    public String det_retailIn(@RequestParam int id) {
        return retailerIn.remove_retailIn(id);

    }
}
