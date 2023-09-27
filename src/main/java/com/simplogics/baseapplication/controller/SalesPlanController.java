package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.annotation.APIResult;
import com.simplogics.baseapplication.dto.SalesPlanRequestDto;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.service.ISalesPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/salesPlan")
public class SalesPlanController {
    @Autowired
    EventStoreMapRepository repository;
    @Autowired
    ISalesPlanService service;
    @RequestMapping(method = RequestMethod.POST)
    @APIResult(message = "#post.success",error_message = "#post.failed")
    public Object addSalesPlan(@RequestBody SalesPlanRequestDto salesPlanRequestDto)throws CustException{
        return service.addSalesPlan(salesPlanRequestDto);
    }
}
