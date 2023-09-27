package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.entity.EventStoreMap;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.service.IEventService;
import com.simplogics.baseapplication.service.IEventStoreMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStoreMapServiceImpl implements IEventStoreMapService {
    @Autowired
    EventStoreMapRepository repository;
    @Override
    public ResultDto getEventStoreMaps(){
        List<EventStoreMap> response = repository.findAll();
        return new ResultDto(true,"GET Successfull",200,response);
    }

}
