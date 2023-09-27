package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.dto.StoreRequestDto;
import com.simplogics.baseapplication.dto.StoreResponseDto;

import java.util.List;

public interface IStoreService{
    StoreResponseDto getStore(int id);

    List<StoreResponseDto> getStores();

    ResultDto addStore(StoreRequestDto storeDTO);

    public ResultDto addStores(List<StoreRequestDto> storeDtos);

    ResultDto updateStore(int id, StoreRequestDto storeDto);

    ResultDto delete(int id);

//    StoreResponseDto addStore(StoreRequestDto);
}
