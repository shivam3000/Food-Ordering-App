package com.foodorderBackend.foodbackend.Domain;

public enum OrderStatus {
    RECEIVED,
    PENDING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
