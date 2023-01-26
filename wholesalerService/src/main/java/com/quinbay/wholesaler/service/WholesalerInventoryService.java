package com.quinbay.wholesaler.service;

import com.quinbay.wholesaler.api.WholeasalerInventoryInterface;
import com.quinbay.wholesaler.kafka.KafkaPublishingService;
import com.quinbay.wholesaler.modal.WholesalerInventory;
import com.quinbay.wholesaler.repository.WholesalerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WholesalerInventoryService implements WholeasalerInventoryInterface {


    @Autowired
    WholesalerInventoryRepository wholesalerInventoryRepository;

    @Autowired
    KafkaPublishingService kafkaPublishingService;

    @Override
    @Cacheable(value = "wholesalerInventorylist")
    public String addProduct(WholesalerInventory w) {
        //for (WholesalerInventory w : inputWholesalerInventory) {
            WholesalerInventory ware = new WholesalerInventory(w.getWholesalerid(), w.getWarehouseid(), w.getProductid(),w.getStock(),w.getPrice());
            //if(w.getId() == )
            wholesalerInventoryRepository.save(ware);
        //}
            return "Wholesaler's product added";
    }

    @Override
//    @Cacheable(value = "wholesalerInventory",key="{#wholeid,#prodid}")
    public ArrayList<WholesalerInventory> findWholesalerInId(int wholeid, int prodid) {
            return wholesalerInventoryRepository.findByWholesaleridAndProductidOrderByStockDesc(wholeid,prodid);
    }

    @Override
    public String deleteProduct(int id) {
        Optional<WholesalerInventory> opt = wholesalerInventoryRepository.findById(id);
        if (opt.isPresent()) {
            wholesalerInventoryRepository.deleteById(id);
            return "Wholesaler data deleted successfully";
        }
        return "Product data not deleted";
    }

    @Override
    public String updateStock(int whoid ,int warid,int pid,int quantity,int inprice) {
        WholesalerInventory emp = null;
        emp = wholesalerInventoryRepository.findByWholesaleridAndWarehouseidAndProductid(whoid,warid,pid);

        if(emp != null){
                emp.setPrice(inprice);
                emp.setStock( emp.getStock() + quantity );
                wholesalerInventoryRepository.save(emp);
                return "Products Stocks added";
            }
        else{
            WholesalerInventory ware = new WholesalerInventory(whoid,warid,pid,quantity,inprice);
            wholesalerInventoryRepository.save(ware);
            return "Product have been added";
        }
    }

    public String sellStock(int whoid ,int warid,int pid,int quantity) {
        WholesalerInventory emp = null;
        emp = wholesalerInventoryRepository.findByWholesaleridAndWarehouseidAndProductid(whoid,warid,pid);



        //Optional<WholesalerInventory> opt = wholesalerInventoryRepository.findByWholesaleridAndWarehouseidAndProductid(whoid,warid,pid);
        if(emp != null) {
            if (emp.getStock() > quantity) {
                emp.setStock(emp.getStock() - quantity);
                wholesalerInventoryRepository.save(emp);
                return "Products Stocks added";
            }
            else{
                return "Insufficient Stock";
            }
        }
        else{
//            WholesalerInventory ware = new WholesalerInventory(id,whoid,warid,pid,quantity,inprice);
//            wholesalerInventoryRepository.save(ware);
            return "Product not avail";
        }
    }


    //findByidAndWarehouseidAndProductid
}
