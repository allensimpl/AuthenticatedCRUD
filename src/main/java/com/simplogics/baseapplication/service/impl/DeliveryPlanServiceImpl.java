package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.DeliveryPlanResponseDto;
import com.simplogics.baseapplication.entity.*;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.repository.*;
import com.simplogics.baseapplication.service.IDeliverPlanService;
import com.simplogics.baseapplication.service.ISalesPlanService;
import com.simplogics.baseapplication.utils.DateMappers;
import com.simplogics.baseapplication.utils.Helper;
import org.apache.poi.ss.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryPlanServiceImpl implements IDeliverPlanService {
    @Autowired
    DeliveryPlanRepository dpRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    EventStoreMapRepository esmRepository;
    @Autowired
    SalesPlanRepository salesPlanRepository;
    @Autowired
    ISalesPlanService salesPlanService;
    @Override
    public List<DeliveryPlan> getDeliveryPlans(){
        List<DeliveryPlan> deliveryPlans = dpRepository.getDeliveryPlans();
        return deliveryPlans;
    }
    @Override
    public List<DeliveryPlanResponseDto> generate(int eventCode, int storeCode) throws CustException {
        int esmID = esmRepository.findId(eventCode,storeCode);
//        for(SalesPlanResponseDto r:allSalesPlan){
//            allSalesPlan.add(Mapper.sales);
//        }
//        Mapper.sales
        List<SalesPlan> salesPlans = salesPlanRepository.getByEsmID(esmID);
        List<EventStoreMap> storeMaps = esmRepository.findAll();
        List<Store> stores = storeRepository.findAll();
        List<Event> events = eventRepository.findAll();
        List<DeliveryPlanResponseDto> dpResponsesDtos = new ArrayList<>();
        List<DeliveryPlan> dpResponse = new ArrayList<>();
        for(SalesPlan salesPlan: salesPlans){
            long esId = salesPlan.getEsId();
            List<Integer> evIdAndStId= Helper.findEventStoreMap(storeMaps,esId);
            int eventId = evIdAndStId.get(0);
            int storeId = evIdAndStId.get(1);
            Event event = Helper.findEventByCode(events,eventId);
            Store store = Helper.findStoreByCode(stores,storeId);
            int deliveryType = store.getDeliveryType();
            Date startDate = event.getStartDate();
            Date endDate = event.getEndDate();
            Date date = salesPlan.getDate();
//            LocalDate date = dateRaw.toInstant()
//                    .atZone(ZoneId.systemDefault())
//                    .toLocalDate();
            if(deliveryType==2){
                date = DateMappers.asDate(DateMappers.asLocalDate(date).minusDays(1));
            }else if(deliveryType == 3){
                date = startDate;
            }
            DeliveryPlanResponseDto responseDto = DeliveryPlanResponseDto.builder()
                    .esmID(salesPlan.getEsId())
                    .date(date)
                    .quantity(salesPlan.getQuantity())
                    .build();
            DeliveryPlan response = DeliveryPlan.builder()
                    .id(salesPlan.getId())
                    .esm_id(salesPlan.getEsId())
                    .date(date)
                    .quantity(salesPlan.getQuantity())
                    .build();
            dpResponsesDtos.add(responseDto);
            dpResponse.add(response);
        }
//        dpRepository.saveAll(dpResponses);
        dpRepository.saveAll(dpResponse);
        return dpResponsesDtos;
    }
}
