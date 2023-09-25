package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.EventRequestDto;
import com.simplogics.baseapplication.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/events")
public class EventController {
    @Autowired
    IEventService service;
    @GetMapping
    public ResultDto getEvents(){
        return service.getEvents();
    }
    @GetMapping("/:{id}")
    public ResultDto getEvent(@PathVariable("id") int id){
        return service.getEvent(id);
    }
    @PostMapping
    public ResultDto addEvent(@RequestBody EventRequestDto event){
        try{
            return service.addEvent(event);
        }catch (Exception e){
            return new ResultDto(false,"POST FAILED",e.getMessage());
        }
    }

    @PostMapping("/bulk")
    public ResultDto addEvents(@RequestBody List<EventRequestDto> eventRequests){
        return service.addEvents(eventRequests);
    }

    @PutMapping("/:{id}")
    public ResultDto updateEvent(@PathVariable("id") int id,@RequestBody EventRequestDto event){
        return service.updateEvent(id,event);
    }

    @DeleteMapping("/:{id}")
    public ResultDto deleteEvent(@PathVariable("id") int id){
        return service.deleteEvent(id);
    }
}
