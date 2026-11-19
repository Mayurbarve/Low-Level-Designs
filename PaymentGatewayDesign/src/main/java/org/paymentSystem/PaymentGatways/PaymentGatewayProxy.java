package org.paymentSystem.PaymentGatways;

import org.paymentSystem.PaymentRequest;

public class PaymentGatewayProxy extends PaymentGateway {
    private PaymentGateway realPaymentGateway;
    private int retries;

    public PaymentGatewayProxy(PaymentGateway gateway, int retries) {
        if (gateway == null) {
            throw new IllegalArgumentException("Gateway is required");
        }
        if (retries < 1) {
            throw new IllegalArgumentException("Retries must be at least 1");
        }
        this.realPaymentGateway = gateway;
        this.retries = retries;
    }

    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        boolean result = false;

        for (int attempt = 0; attempt < retries; ++attempt) {
            if(attempt > 0){
                System.out.println("[proxy] retrying payment " + (attempt + 1) + " for " + paymentRequest.getSender() + ".");
            }
            result = realPaymentGateway.processPayment(paymentRequest);
            if(result) break;
        }
        if(!result){
            System.out.println("[proxy] Payment failed after " + retries
                    + " attempts for " + paymentRequest.getSender() + ".");
        }
        return result;
    }

    @Override
    protected boolean validatePayment(PaymentRequest paymentRequest) {
        return realPaymentGateway.validatePayment(paymentRequest);
    }

    @Override
    protected boolean initiatePayment(PaymentRequest paymentRequest) {
        return realPaymentGateway.initiatePayment(paymentRequest);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest paymentRequest) {
        return realPaymentGateway.confirmPayment(paymentRequest);
    }
}
