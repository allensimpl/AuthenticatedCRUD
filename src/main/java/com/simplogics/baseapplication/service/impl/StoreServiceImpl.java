package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.StoreRequestDto;
import com.simplogics.baseapplication.dto.StoreResponseDto;
import com.simplogics.baseapplication.entity.Store;
import com.simplogics.baseapplication.repository.StoreRepository;
import com.simplogics.baseapplication.service.IStoreService;
import com.simplogics.baseapplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    StoreRepository repository;

    @Override
    public StoreResponseDto getStore(int id){
        Store store = repository.findById(id).orElse(null);
        return Mapper.storeToStoreResponseDto(store);
    }

    @Override
    public List<StoreResponseDto> getStores(){
        List<Store> stores = repository.findAll();
        return Mapper.storeListToListResponse(stores);
    }

    @Override
    public ResultDto addStore(StoreRequestDto storeDTO){
        Store store = Mapper.storeDtoToStoreConverter(storeDTO);
        Store storeResponse = repository.save(store);
        StoreResponseDto storeResponseDto = Mapper.storeToStoreResponseDto(storeResponse);
        return new ResultDto(true,"POST Successfull",200,storeResponseDto);
    }

    @Override
    public List<StoreResponseDto> addStores(List<StoreRequestDto> storeDtos) {
        List<Store> stores = new ArrayList<>();
        for(StoreRequestDto requestDto:storeDtos){
            stores.add(Mapper.storeDtoToStoreConverter(requestDto));
        }
        return Mapper.storeListToListResponse(stores);
    }

}
