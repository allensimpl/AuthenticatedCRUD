package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.DeliveryPlanResponseDto;
import com.simplogics.baseapplication.entity.DeliveryPlan;
import com.simplogics.baseapplication.exception.CustException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IDeliverPlanService {
    @Autowired
    List<DeliveryPlan> getDeliveryPlans();


    List<DeliveryPlanResponseDto> generate(int eventCode, int storeCode) throws CustException;
}
