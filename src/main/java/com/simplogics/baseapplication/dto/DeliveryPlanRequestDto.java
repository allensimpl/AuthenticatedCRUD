package com.simplogics.baseapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPlanRequestDto {
    int eventCode;
    int storeCode;
}
