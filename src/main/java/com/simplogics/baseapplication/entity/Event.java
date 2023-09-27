package com.simplogics.baseapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
