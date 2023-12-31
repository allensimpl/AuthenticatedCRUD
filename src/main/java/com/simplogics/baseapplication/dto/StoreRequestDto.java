package com.simplogics.baseapplication.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreRequestDto extends BaseDto{
    String storeName;
    int storeCode;
    int deliveryType;
}
