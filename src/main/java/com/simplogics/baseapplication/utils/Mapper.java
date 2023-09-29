package com.simplogics.baseapplication.utils;

import com.simplogics.baseapplication.dto.*;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.EventStoreMap;
import com.simplogics.baseapplication.entity.SalesPlan;
import com.simplogics.baseapplication.entity.Store;
import com.simplogics.baseapplication.view.SalesReportView;
import org.springframework.security.web.server.header.CrossOriginEmbedderPolicyServerHttpHeadersWriter;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static EventResponseDTO eventToEventResponseMapper(Event event){
        EventResponseDTO response = new EventResponseDTO();
        response.setId(event.getId());
        response.setEventName(event.getEventName());
        response.setEventCode(event.getEventCode());
        response.setStartDate(event.getStartDate());
        response.setEndDate(event.getEndDate());
        return response;
    }
    public static List<BaseDto> EventListToEventResponseDtoList(List<Event> events){
        List<BaseDto> responses = new ArrayList<>();
        for(Event event:events){
            EventResponseDTO eventResponseDTO = EventResponseDTO.builder()
                    .id(event.getId())
                    .eventCode(event.getEventCode())
                    .eventName(event.getEventName())
                    .build();
            responses.add(eventResponseDTO);
        }
        return responses;
    }
    public static Event eventRequestToEventMapper(EventRequestDto request){
        Event event = new Event();
        event.setEventName(request.getEventName());
        event.setEventCode(request.getEventCode());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        return event;
    }
    public static List<Event> eventRequestDtoListToEventList(List<EventRequestDto> requests){
        List<Event> eventsList = new ArrayList<>();
        for(EventRequestDto request: requests){
            Event event = new Event();
            event.setEventName(request.getEventName());
            event.setEventCode(request.getEventCode());
            event.setStartDate(request.getStartDate());
            event.setEndDate(request.getEndDate());
            eventsList.add(event);
        }
        return eventsList;
    }

    public static StoreResponseDto storeToStoreResponseDto(Store store){
        return StoreResponseDto.builder()
                .id(store.getId())
                .storeCode(store.getStoreCode())
                .storeName(store.getStoreName())
                .deliveryType(store.getDeliveryType())
                .build();
    }

    public static List<Store> storeDtoListToStoreListConverter(List<StoreResponseDto> stores){
        List<Store> storesList = new ArrayList<>();
        for(StoreResponseDto s: stores) {
            storesList.add(storeResponseDtoToStoreConverter(s));
        }
        return storesList;
    }
    public static List<StoreResponseDto> storeListToListResponse(List<Store> stores){
        List<StoreResponseDto> storesResponse = new ArrayList<>();
        for(Store store: stores){
            storesResponse.add(storeToStoreResponseDto(store));
        }
        return storesResponse;
    }

    public static Store storeResponseDtoToStoreConverter(StoreResponseDto store) {
        return Store.builder()
                .id(store.getId())
                .storeCode(store.getStoreCode())
                .storeName(store.getStoreName())
                .deliveryType(store.getDeliveryType())
                .build();
    }
    public static Store storeRequestDtoToStoreConverter(StoreRequestDto request){
        Store store = Store.builder()
                .storeCode(request.getStoreCode())
                .storeName(request.getStoreName())
                .deliveryType(request.getDeliveryType())
                .build();
        return store;
    }

    public static List<Integer> getStoreCodes(List<Store> stores){
        List<Integer> allStoreCodes = new ArrayList<>();
        for(Store s:stores){
            allStoreCodes.add(s.getStoreCode());
        }
        return allStoreCodes;
    }

    public static SalesPlanResponseDto saleToSalesResponseDtoConv(SalesPlan sale){
        return SalesPlanResponseDto.builder()
                .id(sale.getId())
                .esId(sale.getEsId())
                .date(sale.getDate())
                .quantity(sale.getQuantity())
                .build();
    }
    public static List<BaseDto> salesListToSalesResponseDto(List<SalesPlan> sales){
        List<BaseDto> responses = new ArrayList<>();
        for(SalesPlan sale:sales){
            responses.add(Mapper.saleToSalesResponseDtoConv(sale));
        }
        return responses;
    }
    public static SalesReportDto reportViewToReportDto(SalesReportView view){
        return SalesReportDto.builder()
                .eventName(view.getEventName())
                .storeName(view.getStoreName())
                .date(view.getDate())
                .quantity(view.getQuantity()).build();
    }
}