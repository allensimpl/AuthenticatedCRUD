package com.simplogics.baseapplication.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPlan {
    long id;
    long esm_id;
    Date date;
    int quantity;
}
