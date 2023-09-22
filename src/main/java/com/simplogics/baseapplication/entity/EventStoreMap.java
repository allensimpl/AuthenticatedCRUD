package com.simplogics.baseapplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "event_str_map")
public class EventStoreMap {
    @Id
    @GeneratedValue
    int id;
    @Column(name="event_id")
    int eventId;
    @Column(name = "store_id")
    int storeId;
}