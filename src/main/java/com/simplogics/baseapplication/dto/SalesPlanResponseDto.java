package com.simplogics.baseapplication.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesPlanResponseDto extends BaseDto{
    private long id;
    private long esId;
    private Date date;
    private int quantity;

}
