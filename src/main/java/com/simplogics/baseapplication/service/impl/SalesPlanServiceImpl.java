package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.SalesPlanRequestDto;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.SalesPlan;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.repository.EventRepository;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.repository.SalesPlanRepository;
import com.simplogics.baseapplication.service.ISalesPlanService;
import com.simplogics.baseapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class SalesPlanServiceImpl implements ISalesPlanService {
    @Autowired
    EventStoreMapRepository eventStoreMapRepository;
    @Autowired
    SalesPlanRepository repository;
    @Autowired
    EventRepository eventRepository;
    @Override
    public BaseDto addSalesPlan(@RequestBody SalesPlanRequestDto salesPlanRequestDto) throws CustException {
        int eventId = salesPlanRequestDto.getEventId();
        int storeId = salesPlanRequestDto.getStoreId();
        int id = eventStoreMapRepository.findId(eventId,storeId);
        Event event = eventRepository.findByEventCode(eventId);
        Date startDate = event.getStartDate();
        Date endDate = event.getEndDate();
        Date date = salesPlanRequestDto.getDate();
        boolean c1 = date.after(startDate);
        boolean c2 = date.before(endDate);
        int d1 = date.compareTo(startDate);
        int d2 = date.compareTo(endDate);
        if(!date.after(startDate) || !date.before(endDate)){
            throw new CustException("The Date is out of Bounds",500);
        }
        SalesPlan salesPlan = SalesPlan.builder()
                .esId(id)
                .date(salesPlanRequestDto.getDate())
                .quantity(salesPlanRequestDto.getQuantity())
                .build();
        repository.save(salesPlan);
//        return salesPlan;
        return Mapper.saleToSalesResponseDtoConv(salesPlan);
    }
}
