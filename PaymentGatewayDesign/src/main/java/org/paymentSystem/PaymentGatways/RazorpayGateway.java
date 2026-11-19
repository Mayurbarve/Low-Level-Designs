package org.paymentSystem.PaymentGatways;

import org.paymentSystem.BankingSystem.RazorpayBankingSystem;
import org.paymentSystem.PaymentRequest;

public class RazorpayGateway extends PaymentGateway {

    public RazorpayGateway() {
        this.bankingSystem = new RazorpayBankingSystem();
    }

    @Override
    public boolean validatePayment(PaymentRequest paymentRequest) {
        System.out.println("[Razorpay] validating Payment Request for " + paymentRequest.getSender());
        return !(paymentRequest.getAmount() <= 0);
    }

    @Override
    public boolean initiatePayment(PaymentRequest paymentRequest) {
        System.out.println("[Razorpay] Initiating Payment of" + paymentRequest.getAmount() + " "
                + paymentRequest.getCurrency() + " for " + paymentRequest.getSender());

        return bankingSystem.processPayment(paymentRequest.getAmount());
    }

    @Override
    public boolean confirmPayment(PaymentRequest paymentRequest) {
        System.out.println("[Razorpay] Confirming Payment of " + paymentRequest.getAmount() + " for " + paymentRequest.getSender());
        return true;
    }
}
