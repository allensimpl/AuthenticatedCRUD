package com.simplogics.baseapplication.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDTO extends BaseDto{
    private int id;
    private String eventName;
    private int eventCode;
    private Date startDate;
    private Date endDate;
}
