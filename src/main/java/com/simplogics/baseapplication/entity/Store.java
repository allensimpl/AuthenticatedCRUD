package com.simplogics.baseapplication.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "str_name")
    String storeName;

    @Column(name = "str_code")
    int storeCode;

    @Column(name = "delivery_type",columnDefinition = "default integer 1")
    int deliveryType;
}
