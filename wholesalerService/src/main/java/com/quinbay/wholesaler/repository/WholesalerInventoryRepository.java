package com.quinbay.wholesaler.repository;

import com.quinbay.wholesaler.modal.WholesalerInventory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface WholesalerInventoryRepository extends JpaRepository<WholesalerInventory,Integer> {
    //WholesalerInventory findByidandwarehouseid (int id,int wid
//    @Query("select * form wholesalerInventory where wholesalerid=?1 and productid=?2 orderby stock Desc")
    ArrayList<WholesalerInventory> findByWholesaleridAndProductidOrderByStockDesc(int wholeid, int pid);
    WholesalerInventory findByWholesaleridAndWarehouseidAndProductid(int whoid, int warid, int pid);

}
