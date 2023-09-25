package com.simplogics.baseapplication.utils;

import com.simplogics.baseapplication.dto.EventRequestDto;
import com.simplogics.baseapplication.dto.EventResponseDTO;
import com.simplogics.baseapplication.dto.StoreRequestDto;
import com.simplogics.baseapplication.dto.StoreResponseDto;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.Store;
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
        StoreResponseDto responseDto = new StoreResponseDto();
        responseDto.setId(responseDto.getId());
        responseDto.setStoreCode(responseDto.getStoreCode());
        responseDto.setStoreName(responseDto.getStoreName());
        return responseDto;
    }

    public static List<StoreResponseDto> storeListToListResponse(List<Store> stores){
        List<Store> storesResponse = new ArrayList<>();
        for(Store store: stores){
            StoreResponseDto responseDto = new StoreResponseDto();
            responseDto.setId(store.getId());
            responseDto.setStoreCode(store.getStoreCode());
            responseDto.setStoreName(store.getStoreName());
        }
        return Mapper.storeListToListResponse(storesResponse);
    }
    public static Store storeDtoToStoreConverter(StoreRequestDto request){
        Store store = new Store();
        store.setStoreCode(request.getStoreCode());
        store.setStoreName(request.getStoreName());
        return store;
    }


}