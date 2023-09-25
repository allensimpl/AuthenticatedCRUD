package com.simplogics.baseapplication.dto;

import lombok.Getter;

import java.util.Date;


@Getter
public class EventResponseDTO {
    private int id;
    private String eventName;
    private int eventCode;
    private Date startDate;
    private Date endDate;
    public EventResponseDTO(){

    }
    public EventResponseDTO(int id, String eventName, int eventCode, Date startDate, Date endDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventCode = eventCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
