package com.simplogics.baseapplication.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportDto extends BaseDto {
    String eventName;
    String storeName;
    Date date;
    int quantity;
}
