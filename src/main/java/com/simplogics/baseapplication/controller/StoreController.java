package com.simplogics.baseapplication.controller;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.StoreRequestDto;
import com.simplogics.baseapplication.dto.StoreResponseDto;
import com.simplogics.baseapplication.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}"+"/secure/store")
public class StoreController {
    @Autowired
    IStoreService service;
    @GetMapping
    public ResultDto getStores(){
        List<StoreResponseDto> stores =  service.getStores();
        ResultDto result = new ResultDto(true,"GET Successfull",200,stores);
        return new ResultDto(true,"GET Successfull",200,stores);
    }
    @GetMapping("/:{id}")
    public ResultDto getStore(@PathVariable("id") int id){
        StoreResponseDto store = service.getStore(id);
        return new ResultDto(true,"GET Successfull",200,store);
    }

    @PostMapping
    public ResultDto addStore(@RequestBody StoreRequestDto storeDTO){
        return service.addStore(storeDTO);
    }
    @PostMapping("/bulk")
    public ResultDto addStores(@RequestBody List<StoreRequestDto> storeDtos){
        return service.addStores(storeDtos);
    }

    @PutMapping("/:{id}")
    public ResultDto updateStore(@PathVariable("id") int id, @RequestBody StoreRequestDto storeDto){
        return service.updateStore(id,storeDto);
    }

    @DeleteMapping("/:{id}")
    public ResultDto deleteStore(@PathVariable("id") int id){
        return service.delete(id);
    }
}
