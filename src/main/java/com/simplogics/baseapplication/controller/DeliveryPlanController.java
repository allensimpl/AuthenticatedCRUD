package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.annotation.APIResult;
import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.DeliveryPlanRequestDto;
import com.simplogics.baseapplication.dto.DeliveryPlanResponseDto;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.service.IDeliveryPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/deliveryPlan")
public class DeliveryPlanController {
    @Autowired
    IDeliveryPlanService service;
    @RequestMapping(method = RequestMethod.GET)
    public Object getDeliveryPlans(){
        return service.getDeliveryPlans();
    }

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    @APIResult(message =
            "",error_message = "")
    public Object generate(@RequestBody DeliveryPlanRequestDto requestDto) throws CustException {
        int eventCode = requestDto.getEventCode();
        int storeCode = requestDto.getStoreCode();
        List<DeliveryPlanResponseDto> response = service.generate(eventCode,storeCode);
        return response;
    }
}

