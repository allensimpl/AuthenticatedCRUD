package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.SalesPlan;
import com.simplogics.baseapplication.view.SalesReportView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesPlanRepository extends JpaRepository<SalesPlan,Integer> {
    @Query(value = "SELECT e.ev_name AS eventName, s.str_name AS storeName, sp.date AS date, sp.quantity AS quantity "+
            "FROM sales_plan sp "+
            "JOIN event_str_map esm ON sp.evsm_id = esm.id "+
            "JOIN store s ON esm.store_id = s.str_code "+
            "JOIN event e ON esm.event_id = e.ev_code;"
            ,nativeQuery = true)
    List<SalesReportView> generateReport();
}

//SELECT e.ev_name AS eventName, s.str_name AS storeName, sp.date AS date, sp.quantity AS quantity FROM sales_plan sp JOIN event_str_map esm ON sp.evsm_id = esm.id JOIN Store s ON esm.store_id = s.id JOIN Event e ON esm.event_id = e.id;

//    SELECT e.ev_name AS eventName, s.str_name AS storeName, sp.date AS date, sp.quantity AS quantity
//        FROM sales_plan sp
//        JOIN event_str_map esm ON sp.evsm_id = esm.id
//        JOIN store s ON esm.store_id = s.id JOIN event e ON esm.event_id = e.id;
