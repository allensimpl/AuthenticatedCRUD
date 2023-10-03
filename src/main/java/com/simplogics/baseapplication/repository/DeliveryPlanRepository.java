package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.DeliveryPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPlanRepository extends JpaRepository<DeliveryPlan,Integer> {

//    List<DeliveryPlan> getDeliveryPlans();
}