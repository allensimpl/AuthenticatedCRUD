package com.simplogics.baseapplication.repository;

import com.simplogics.baseapplication.entity.DeliveryPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPlanRepository extends JpaRepository<DeliveryPlan,Integer> {
    List<DeliveryPlan> getDeliveryPlans();
}