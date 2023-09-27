package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.PaginationDto;
import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.EventRequestDto;
import com.simplogics.baseapplication.exception.CustException;

import java.util.List;

public interface IEventService {

    BaseDto addEvent(EventRequestDto eventRequest) throws CustException;

    List<BaseDto> addEvents(List<EventRequestDto> eventRequests);
    PaginationDto getEvents(int pageNo, int pageSize, String sort, Boolean descending, String search);

    BaseDto getEvent(int id) throws CustException;

    BaseDto updateEvent(int id, EventRequestDto eventRequest) throws CustException;

    String deleteEvent(int id) throws CustException;

    BaseDto unpublish(int id) throws CustException;

    BaseDto publishEvent(int id) throws CustException;
}