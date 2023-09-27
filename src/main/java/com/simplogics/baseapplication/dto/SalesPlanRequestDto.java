package com.simplogics.baseapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesPlanRequestDto extends BaseDto{
    private int eventId;
    private int storeId;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date;
    private int quantity;
}
