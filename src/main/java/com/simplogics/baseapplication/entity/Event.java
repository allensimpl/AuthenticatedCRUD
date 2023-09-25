package com.simplogics.baseapplication.entity;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "ev_name")
    private String eventName;
    @Column(name = "ev_code")
    private int eventCode;
    @Column(name = "start_date")
     private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "published",columnDefinition = "boolean default 0")
    private boolean published;

    public Event() {
    }

    public Event(int id, String eventName, int eventCode, Date startDate, Date endDate, boolean published) {
        this.id = id;
        this.eventName = eventName;
        this.eventCode = eventCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
