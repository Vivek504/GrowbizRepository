package com.growbiz.backend.Enums;

import java.util.Arrays;

public enum PaymentStatus {
    SUCCESS("payment_intent.succeeded"),
    CREATED("payment_intent.created"),
    CANCELED("payment_intent.canceled"),
    FAILED("payment_intent.payment_failed");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public static PaymentStatus getStatusFromValue(String value) {
        return Arrays.stream(PaymentStatus.values()).filter(paymentStatus -> paymentStatus.status.equals(value)).findFirst().get();
    }
}
