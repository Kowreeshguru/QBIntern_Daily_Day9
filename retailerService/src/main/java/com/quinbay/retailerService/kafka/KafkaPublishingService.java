package com.quinbay.retailerService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.retailerService.Model.FinanceDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublishingService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void wholesalerFinanceInfo (FinanceDepartment finance){
        try{
            System.out.println("Kafka Publisher called");
            kafkaTemplate.send("send.financeinfo",this.objectMapper.writeValueAsString(finance));
        }
        catch (Exception e){
            System.out.println("Error Occurred"+e);
        }
    }
}
