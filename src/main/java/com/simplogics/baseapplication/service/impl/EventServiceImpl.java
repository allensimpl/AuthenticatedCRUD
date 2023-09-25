package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.EventRequestDto;
import com.simplogics.baseapplication.dto.EventResponseDTO;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.repository.EventRepository;
import com.simplogics.baseapplication.service.IEventService;
import com.simplogics.baseapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventRepository repository;

    @Override
    public ResultDto addEvent(EventRequestDto eventRequest) throws Exception {
        if(repository.countEventCode(eventRequest.getEventCode())>0){
            throw new Exception("The event already exists");
        }
        Event event = Mapper.eventRequestToEventMapper(eventRequest);
        Event response = repository.save(event);
        EventResponseDTO responseDTO = Mapper.eventToEventResponseMapper(response);
        return new ResultDto(true,"POST Successful",200,responseDTO);
    }

    @Override
    public ResultDto addEvents(List<EventRequestDto> eventRequests){
        List<Event> events = Mapper.eventRequestDtoListToEventList(eventRequests);
        return new ResultDto(true,"POST Successful",200,events);
    }
    @Override
    public ResultDto getEvents(){
        List<Event> events = repository.findAll();
        return new ResultDto(true,"GET Successful",200,events);
    }

    @Override
    public ResultDto getEvent(int id){
        Event event = repository.findById(id).orElse(null);
        return new ResultDto(true,"GET Successful",200,event);
    }
    @Override
    public ResultDto updateEvent(int id, EventRequestDto eventRequest){
        //Write Exception to check if ID not there condition
        Event event = repository.findById(id).orElse(null);
        event.setEventCode(eventRequest.getEventCode());
        event.setEventName(eventRequest.getEventName());
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        Event saveResponse = repository.save(event);
        return new ResultDto(true,"PUT Successfull",200,saveResponse);
    }

    @Override
    public ResultDto deleteEvent(int id){
        //Write Exception to check if ID not there condition
        repository.deleteById(id);
        return new ResultDto(true,"DELETE Successfull",200,"Deleted "+id);
    }
}