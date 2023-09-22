package com.simplogics.baseapplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "str_name")
    String storeName;
    @Column(name = "str_code")
    int storeCode;
}
