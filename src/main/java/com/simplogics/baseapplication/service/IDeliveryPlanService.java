package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.DeliveryPlanResponseDto;
import com.simplogics.baseapplication.entity.DeliveryPlan;
import com.simplogics.baseapplication.exception.CustException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDeliveryPlanService {
    List<DeliveryPlan> getDeliveryPlans();

    List<DeliveryPlanResponseDto> generate(int eventCode, int storeCode) throws CustException;
}
