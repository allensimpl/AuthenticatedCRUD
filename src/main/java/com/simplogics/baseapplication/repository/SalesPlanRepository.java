package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.SalesPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPlanRepository extends JpaRepository<SalesPlan,Integer> {
}
