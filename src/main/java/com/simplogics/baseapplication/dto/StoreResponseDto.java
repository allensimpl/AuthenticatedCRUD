package com.simplogics.baseapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponseDto {
    private int id;
    String storeName;
    int storeCode;
    int deliveryType;
}