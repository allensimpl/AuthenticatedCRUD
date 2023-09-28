package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.SalesPlanRequestDto;
import com.simplogics.baseapplication.exception.CustException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public interface ISalesPlanService {
    BaseDto addSalesPlan(@RequestBody SalesPlanRequestDto salesPlanRequestDto) throws CustException;

//    public List<BaseDto> generateReport();

    public List<BaseDto> generateReport(HttpServletResponse response) throws IOException;
//    List<BaseDto> generateReport(HttpServletResponse response) throws IOException
}