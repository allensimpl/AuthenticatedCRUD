package com.simplogics.baseapplication.dto;

import javax.persistence.Column;

public class StoreRequestDto {
    String storeName;
    int storeCode;

    public StoreRequestDto() {
    }

    public StoreRequestDto(String storeName, int storeCode) {
        this.storeName = storeName;
        this.storeCode = storeCode;
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
