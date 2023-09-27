package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.annotation.APIResult;
import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.EventRequestDto;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/events")
public class EventController {
    @Autowired
    IEventService service;
    @RequestMapping(method = RequestMethod.GET)
    @APIResult(message = "#get.success",error_message = "#get.failed")
    public Object getEvents(@RequestParam(name = "pageNo",required = true)Integer pageNo,
                            @RequestParam(name = "pageSize",required = true)Integer pageSize,
                            @RequestParam(name = "sort",required = false,defaultValue = "ev_name")String sort,
                            @RequestParam(name = "descending",required = false,defaultValue = "false")boolean descending,
                            @RequestParam(name = "search",required = false)String search){
        return service.getEvents(pageNo,pageSize,sort,descending,search);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @APIResult(message = "#get.success",error_message = "#get.failed")
    public Object getEvent(@PathVariable("id") int id) throws CustException {
        return service.getEvent(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    @APIResult(message = "#post.success",error_message = "#post.failed")
    public Object addEvent(@RequestBody EventRequestDto event) throws CustException {
        return service.addEvent(event);
    }

    @RequestMapping(value = "/bulk",method = RequestMethod.POST)
    @APIResult(message = "#post.success",error_message = "#post.failed")
    public Object addEvents(@RequestBody List<EventRequestDto> eventRequests){
        return service.addEvents(eventRequests);
    }
    @RequestMapping(value = "/publish/{id}",method = RequestMethod.POST)
    @APIResult(message = "#publish.success",error_message = "#publish.error")
    public Object publishEvents(@PathVariable("id")int id) throws CustException {
        return service.publishEvent(id);
    }
    @RequestMapping(value = "/unpublish/{id}",method = RequestMethod.POST)
    @APIResult(message = "#unpublish.success",error_message = "#unpublish.error")
    public Object unpublishEvents(@PathVariable("id")int id) throws CustException {
        return service.unpublish(id);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @APIResult(message = "#put.succes",error_message = "#put.failed")
    public Object updateEvent(@PathVariable("id") int id,@RequestBody EventRequestDto event) throws CustException {
        return service.updateEvent(id,event);
    }

    @RequestMapping(value = "/:{id}",method = RequestMethod.DELETE)
    @APIResult(message = "#delete.success",error_message = "#delete.failed")
    public Object deleteEvent(@PathVariable("id") int id) throws CustException {
        return service.deleteEvent(id);
    }
}

//TODO Learn/Read about Search functioning in depth