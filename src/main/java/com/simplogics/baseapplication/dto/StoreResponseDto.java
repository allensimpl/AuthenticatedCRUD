package com.simplogics.baseapplication.dto;

public class StoreResponseDto {
    private int id;
    String storeName;
    int storeCode;

    public StoreResponseDto() {
    }

    public StoreResponseDto(int id, String storeName, int storeCode) {
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