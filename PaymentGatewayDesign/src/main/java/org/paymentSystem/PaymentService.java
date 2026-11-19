package org.paymentSystem;

import org.paymentSystem.PaymentGatways.PaymentGateway;

enum PaymentService {
    INSTANCE;

    public boolean processPayment(PaymentGateway paymentGateway, PaymentRequest paymentRequest) {
        if (paymentGateway == null) {
            System.out.println("No payment gateway selected.");
            return false;
        }
        return paymentGateway.processPayment(paymentRequest);
    }

}
