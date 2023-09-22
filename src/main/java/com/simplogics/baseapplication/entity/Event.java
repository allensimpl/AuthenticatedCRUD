package com.simplogics.baseapplication.entity;

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
    @Column(name = "published")
    private boolean published;
}
