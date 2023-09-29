package com.simplogics.baseapplication.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPlanResponseDto {
    int id;
    long esmID;
    Date date;
    int quantity;
}
