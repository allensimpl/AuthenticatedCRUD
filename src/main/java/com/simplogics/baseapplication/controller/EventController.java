package com.simplogics.baseapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/events")
public class EventController {
    @Autowired

    @PostMapping("/publish")

}
