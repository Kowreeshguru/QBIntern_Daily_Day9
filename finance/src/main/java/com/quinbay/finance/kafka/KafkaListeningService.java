package com.quinbay.finance.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.finance.modal.FinanceDepartment;
import com.quinbay.finance.service.FinanceService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListeningService {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FinanceService financeService;

    @KafkaListener(topics = "send.financeinfo",groupId = "warehouse")
    public void getWholesalerInfo (ConsumerRecord<?,String> consumerRecord){
        FinanceDepartment finance = null ;
        try{
            finance = objectMapper.readValue(consumerRecord.value(),
                    new TypeReference<FinanceDepartment>(){
                    });
//            System.out.println(finance.getTotalamount());
//            System.out.println(finance.getBalancepay());
//            System.out.println(finance.getBalancepay()-finance.getTotalamount());
        financeService.updateStock(finance.getBuyerid(),finance.getSellerid(),finance.getProductid(),finance.getQuantity(),finance.getTotalamount(),finance.getBalancepay());
        }catch (Exception e){

        }
    }
}
