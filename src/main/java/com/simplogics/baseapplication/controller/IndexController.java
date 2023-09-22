package com.simplogics.baseapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.data.rest.base-path}")
public class IndexController {
    @GetMapping("/secure/test")
    public String testing(){
        System.out.println("Here");
        return "Tested";
    }
}
