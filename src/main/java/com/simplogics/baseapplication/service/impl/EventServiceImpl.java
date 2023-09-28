package com.simplogics.baseapplication.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.simplogics.baseapplication.dto.*;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.EventStoreMap;
import com.simplogics.baseapplication.entity.Store;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.repository.EventRepository;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.repository.StoreRepository;
import com.simplogics.baseapplication.service.IEventService;
import com.simplogics.baseapplication.utils.Mapper;
import org.apache.commons.beanutils.MappedPropertyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventRepository repository;
    @Autowired
    EventStoreMapRepository eventStoreMapRepository;
    @Autowired
    StoreRepository storeRepository;

    @Override
    public BaseDto addEvent(EventRequestDto eventRequest) throws CustException {
        if(repository.countEventCode(eventRequest.getEventCode())>0){
            throw new CustException("The event already exists",500);
        }
        Event event = Mapper.eventRequestToEventMapper(eventRequest);
        Event response = repository.save(event);
        return Mapper.eventToEventResponseMapper(response);
//        return new ResultDto(true,"POST Successful",200,responseDTO);
    }

    @Override
    public List<BaseDto> addEvents(List<EventRequestDto> eventRequests){
        List<Event> events = Mapper.eventRequestDtoListToEventList(eventRequests);
        events = repository.saveAll(events);
        return Mapper.EventListToEventResponseDtoList(events);
//        return new ResultDto(true,"POST Bulk Successful",200,events);
    }

    @Override
    public PaginationDto getEvents(int pageNo, int pageSize, String sort, Boolean descending, String search){
        Pageable pagingParams = PageRequest.of(pageNo-1, pageSize,
                JpaSort.unsafe(descending ? Sort.Direction.DESC : Sort.Direction.ASC, "(" + sort + ")"));

        Page<Event> events = repository.findAllEvents(search,pagingParams);
        List<EventResponseDTO> responses = new ArrayList<>();
        for(Event event:events.toList()){
            EventResponseDTO eventResponseDto = new EventResponseDTO(event.getId(),event.getEventName(),event.getEventCode(),event.getStartDate(),event.getEndDate());
            responses.add(eventResponseDto);
        }
        return new PaginationDto(responses,events.getTotalElements());
    }


    @Override
    public BaseDto getEvent(int id) throws CustException {
        Event event = repository.findById(id).orElse(null);
        if(event==null){
            throw new CustException("#event.not.found");
        }
        return Mapper.eventToEventResponseMapper(event);
//        return new ResultDto(true,"GET Successful",200,event);
    }
    @Override
    public BaseDto updateEvent(int id, EventRequestDto eventRequest) throws CustException {
        //Write Exception to check if ID not there condition
        Event event = repository.findById(id).orElse(null);
        if(event==null){
            throw new CustException("#event.not.found",500);
        }
        event.setEventCode(eventRequest.getEventCode());
        event.setEventName(eventRequest.getEventName());
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        Event saveResponse = repository.save(event);
        return Mapper.eventToEventResponseMapper(saveResponse);
//        return new ResultDto(true,"PUT Successfull",200,saveResponse);
    }

    @Override
    public String deleteEvent(int id) throws CustException {
        //TODO Write Exception to check if ID not there condition
        if(repository.countEventID(id)<0){
            throw new CustException("#id.not.found",500);
        }
        repository.deleteById(id);
        return "Deleted "+id;
//        return new ResultDto(true,"DELETE Successfull",200,"Deleted "+id);
    }
    @Override
    public BaseDto publishEvent(int id) throws CustException {
        Event event = repository.findById(id)
                .orElseThrow(()->new CustException("#event.not.found",500));

        int eventCode = event.getEventCode();
        if(eventStoreMapRepository.eventCount(eventCode)>0){
            throw new CustException("#already.published",500);
        }

        List<Store> allStores = storeRepository.findAll();
        List<Integer> storeCodes = Mapper.getStoreCodes(allStores);
        List<EventStoreMap> eventStoreMaps = new ArrayList<>();
        for(Integer i:storeCodes){
            EventStoreMap eventStoreMap = new EventStoreMap();
            eventStoreMap.setEventId(eventCode);
            eventStoreMap.setStoreId(i);
            eventStoreMaps.add(eventStoreMap);
        }
        event.setPublished(true);
        repository.save(event);
        eventStoreMapRepository.saveAll(eventStoreMaps);
        return Mapper.eventToEventResponseMapper(event);
//        return new ResultDto(true,"Updated "+id,200,event);
    }
    @Override
    @Transactional
    public BaseDto unpublish(int id) throws CustException {
        Event event = repository.findById(id).orElse(null);
        if(event==null){
            throw new CustException("#event.not.found");
        }
        int eventCode = event.getEventCode();
        if(!event.isPublished()){
            throw new CustException("#not.published");
        }
        eventStoreMapRepository.unpublishDelete(eventCode);
        event.setPublished(false);
        repository.save(event);
        return Mapper.eventToEventResponseMapper(event);
    }
}