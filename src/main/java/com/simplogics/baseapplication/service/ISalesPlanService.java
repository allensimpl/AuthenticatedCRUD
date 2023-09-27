package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.SalesPlanRequestDto;
import com.simplogics.baseapplication.exception.CustException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ISalesPlanService {
    BaseDto addSalesPlan(@RequestBody SalesPlanRequestDto salesPlanRequestDto) throws CustException;
}
