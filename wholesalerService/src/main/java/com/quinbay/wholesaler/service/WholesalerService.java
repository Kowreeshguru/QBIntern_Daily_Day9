package com.quinbay.wholesaler.service;

import com.quinbay.wholesaler.api.WholesalerInterface;
import com.quinbay.wholesaler.kafka.KafkaPublishingService;
import com.quinbay.wholesaler.modal.FinanceDepartment;
import com.quinbay.wholesaler.modal.Wholesaler;
import com.quinbay.wholesaler.repository.WholesalerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WholesalerService implements WholesalerInterface {

    @Autowired
    WholesalerRepository wholesalerRepository;

    @Autowired
    KafkaPublishingService kafkaPublishingService;

    @Override
    @Cacheable(value = "wholesalerlist")
    public ArrayList<Wholesaler> findAllWholesaler() {
        return (ArrayList<Wholesaler>) wholesalerRepository.findAll();
    }


    @Override
    @Cacheable(value="Wholesaler", key="#wid")
    public Wholesaler findWholesalerId(int wid) {
        //kafkaPublishingService.wholesalerInfo(opt.get());
        try{
            return wholesalerRepository.findById(wid);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public String addWholesaler(ArrayList<Wholesaler> inputWholesaler) {
        for (Wholesaler w : inputWholesaler) {
            Wholesaler wholesaler = new Wholesaler(w.getId(), w.getWref(), w.getWname());
            wholesalerRepository.save(wholesaler);
        }
        return "Wholesaler added";
    }

    @Override
    public void buyProduct(FinanceDepartment finance){
        FinanceDepartment fin = new FinanceDepartment(finance.getId(), finance.getBuyerid(),finance.getSellerid(),finance.getProductid(),finance.getQuantity(),finance.getTotalamount(),finance.getBalancepay());
        kafkaPublishingService.wholesalerFinanceInfo(fin);
    }


    @Override
    public void deleteAllData() {
        wholesalerRepository.deleteAll();
    }

    @Override
    public String deleteWholesaler(int id) {
        try{
            wholesalerRepository.deleteById(id);
            return ("Deleted successfully");
        }catch(Exception e){
            return ("Not deleted");
        }
    }

    public ResponseEntity<String> updateWholesaler(int id, String wref) {
        try {
            Wholesaler wholesaler = wholesalerRepository.findById(id);
            wholesaler.setWref(wref);
            wholesalerRepository.save(wholesaler);
            return new ResponseEntity("Successfully update", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, Id not found", HttpStatus.BAD_REQUEST);
        }
    }
}
