package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.dto.EventStoreMapDto;
import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.entity.EventStoreMap;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.service.IEventService;
import com.simplogics.baseapplication.service.IEventStoreMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/eventStoreMap")
public class EventStoreMapController {
    @Autowired
    IEventStoreMapService service;

    @GetMapping
    public Object getEventStoreMaps(){
        return service.getEventStoreMaps();
    }
}
