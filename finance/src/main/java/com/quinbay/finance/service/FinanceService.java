package com.quinbay.finance.service;

import com.quinbay.finance.api.FinanceInterface;
//import com.quinbay.finance.kafka.KafkaListeningService;
import com.quinbay.finance.kafka.KafkaListeningService;
import com.quinbay.finance.modal.FinanceDepartment;
import com.quinbay.finance.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FinanceService implements FinanceInterface {

    //@Autowired
    //KafkaListeningService kafkaListeningService;

    @Autowired
    FinanceRepository financeRepository;
    @Autowired
    KafkaListeningService kafkaListeningService;

//    @Override
//    public String addFinance(ArrayList<FinanceDepartment> inputFinance) {
//        for (FinanceDepartment w : inputFinance) {
//            FinanceDepartment finance = new FinanceDepartment( w.getId(), w.getBuyerid() , w.getSellerid() , w.getProductid() , w.getQuantity() ,w.getTotalamount(), w.getBalancepay());
//            //if(w.)
//            financeRepository.save(finance);
//        }
//        return "Finance Details added";
//    }

    @Override
    public ArrayList<FinanceDepartment> findPaymentByID(int buyerid,int sellerid,int productid) {
        return financeRepository.findByBuyeridAndSelleridAndProductid(buyerid,sellerid,productid);
    }

    @Override
    public ArrayList<FinanceDepartment> findAllFinance() {
        return (ArrayList<FinanceDepartment>) financeRepository.findAll();
    }

    @Override
    public String updateStock(int buyerid ,int sellerid,int productid,int quantity,int totalamt,int paidamount) {
        ArrayList<FinanceDepartment> fin = null;
        fin = financeRepository.findByBuyeridAndSelleridAndProductid(buyerid, sellerid, productid);
        System.out.println(fin.size());
        if (fin.size() == 0) {
            FinanceDepartment ware = new FinanceDepartment(buyerid, sellerid, productid, quantity, totalamt, totalamt - paidamount);
            financeRepository.save(ware);
            return "Product Bill have been added";
        } else if (fin.size() == 1) {
            int bal = fin.get(0).getBalancepay() - paidamount;
            FinanceDepartment iterate = new FinanceDepartment(buyerid, sellerid, productid, quantity, totalamt, bal);
            financeRepository.save(iterate);
            return "Products Bill Updated";
        } else if(fin.size()>1){
            int bal = fin.get(fin.size()-1).getBalancepay() - paidamount;
            if(fin.get(fin.size()-1).getBalancepay() < paidamount){
                int ret = Math.abs(fin.get(fin.size()-1).getBalancepay() - paidamount);
                System.out.println(ret);
                bal = 0;
                FinanceDepartment iterate = new FinanceDepartment(buyerid, sellerid, productid, quantity, totalamt, bal);
                financeRepository.save(iterate);
                return "You have paid more and here is your balance amount : "+ ret +" ";
            }
            FinanceDepartment iterate = new FinanceDepartment(buyerid, sellerid, productid, quantity, totalamt, bal);
            financeRepository.save(iterate);
            return "Products Bill Updated";
        }
        return "Empty";
    }



//        if(fin != null){//null//1//empty
//            int bal= fin.getBalancepay() - paidamount ;
//            FinanceDepartment iterate = new FinanceDepartment(buyerid,sellerid,productid,quantity,totalamt,bal);
//            financeRepository.save(iterate);
//            return "Products Bill Updated";
//        }
//        else{
//            FinanceDepartment ware = new FinanceDepartment(buyerid,sellerid,productid,quantity,totalamt,totalamt - paidamount);
//            financeRepository.save(ware);
//            return "Product Bill have been added";
//        }


    @Override
    public void deleteAllData() {
        financeRepository.deleteAll();
    }


}
