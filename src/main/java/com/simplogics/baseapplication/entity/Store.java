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

    public Store() {
    }

    public Store(int id, String storeName, int storeCode) {
        this.id = id;
        this.storeName = storeName;
        this.storeCode = storeCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }
}
