package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.EventRequestDto;

import java.util.List;

public interface IEventService {

    ResultDto addEvent(EventRequestDto event) throws Exception;

    ResultDto addEvents(List<EventRequestDto> eventRequests);

    ResultDto getEvents();


    ResultDto getEvent(int id);

    ResultDto updateEvent(int id, EventRequestDto eventRequest);

    ResultDto deleteEvent(int id);
}
