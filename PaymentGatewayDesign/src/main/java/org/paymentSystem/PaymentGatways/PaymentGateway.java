package org.paymentSystem.PaymentGatways;

import org.paymentSystem.BankingSystem.BankingSystem;
import org.paymentSystem.PaymentRequest;

public abstract class PaymentGateway {
    protected BankingSystem bankingSystem;

    public PaymentGateway() {
        this.bankingSystem = null;
    }

    public boolean processPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null
                || paymentRequest.getSender() == null || paymentRequest.getSender().isBlank()
                || paymentRequest.getRecipient() == null || paymentRequest.getRecipient().isBlank()
                || !Double.isFinite(paymentRequest.getAmount()) || paymentRequest.getAmount() <= 0
                || paymentRequest.getCurrency() == null || paymentRequest.getCurrency().isBlank()) {
            System.out.println("[Payment Gateway] invalid payment request");
            return false;
        }

        if (!validatePayment(paymentRequest)) {
            System.out.println("[Payment Gateway] validation Failed for " + paymentRequest.getSender());
            return false;
        }
        if (!initiatePayment(paymentRequest)) {
            System.out.println("[Payment Gateway] initiation Failed for " + paymentRequest.getSender());
            return false;
        }
        if (!confirmPayment(paymentRequest)) {
            System.out.println("[Payment Gateway] confirmation Failed for " + paymentRequest.getSender());
            return false;
        }
        return true;
    }

    protected abstract boolean validatePayment(PaymentRequest paymentRequest);

    protected abstract boolean initiatePayment(PaymentRequest paymentRequest);

    protected abstract boolean confirmPayment(PaymentRequest paymentRequest);

}
