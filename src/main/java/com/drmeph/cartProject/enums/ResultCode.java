package com.drmeph.cartProject.enums;

/**
 * Created by kdorfer on 2016-11-07.
 */
public enum ResultCode {
    GET_LIST_PRODUCTS_FAILED(0, "resultCode.0"),
    GET_LIST_PRODUCTS_SUCCESS(1, "resultCode.1"),
    ADD_PRODUCT_FAILED(2, "resultCode.2"),
    ADD_PRODUCT_SUCCESS(3, "resultCode.3"),
    DELETE_PRODUCT_FAILED(4, "resultCode.4"),
    DELETE_PRODUCT_SUCCESS(5, "resultCode.5"),
    LOCK_PRODUCT_FAILED(6, "resultCode.6"),
    LOCK_PRODUCT_SUCCESS(7, "resultCode.7"),
    UNLOCK_PRODUCT_FAILED(8, "resultCode.8"),
    UNLOCK_PRODUCT_SUCCESS(9, "resultCode.9"),
    GET_ADMIN_LIST_PRODUCT_FAILED(10, "resultCode.10"),
    GET_ADMIN_LIST_PRODUCT_SUCCESS(11, "resultCode.11"),
    SHOW_PRODUCT_FAILED(12, "resultCode.12"),
    SHOW_PRODUCT_SUCCESS(13, "resultCode.13"),
    RATE_PRODUCT_FAILED(14, "resultCode.14"),
    RATE_PRODUCT_SUCCESS(15, "resultCode.15");

    private int id;
    private String descriptionKey;

    ResultCode(int id, String descriptionKey) {
        this.id = id;
        this.descriptionKey = descriptionKey;
    }

    public int getId() {
        return id;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }
}
