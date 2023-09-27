package com.simplogics.baseapplication.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto extends BaseDto{
    private List<?> data;
    private long count;
}
