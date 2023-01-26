package com.quinbay.finance.repository;

import com.quinbay.finance.modal.FinanceDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FinanceRepository extends JpaRepository<FinanceDepartment,Integer>{
    ArrayList<FinanceDepartment> findByBuyeridAndSelleridAndProductid(int buyerid, int sellerid, int productid);
}
