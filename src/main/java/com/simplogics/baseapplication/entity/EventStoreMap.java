package com.simplogics.baseapplication.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "event_str_map")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventStoreMap {
    @Id
    @GeneratedValue
    int id;
    @Column(name="event_id")
    int eventId;
    @Column(name = "store_id")
    int storeId;
}