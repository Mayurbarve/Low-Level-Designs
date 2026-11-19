package org.paymentSystem.PaymentGatways;

import org.paymentSystem.BankingSystem.BankingSystem;
import org.paymentSystem.BankingSystem.PaytmBankingSystem;
import org.paymentSystem.PaymentRequest;

public class PaytmGateway extends PaymentGateway{

    public PaytmGateway(){
        this.bankingSystem = new PaytmBankingSystem();
    }

    @Override
    public boolean validatePayment(PaymentRequest paymentRequest){
        System.out.println("[paymentGateway] Validating Payment Request for " + paymentRequest.getSender());
        if(paymentRequest.getAmount() <= 0 || !"INR".equals(paymentRequest.getCurrency())){
            return false;
        }
        return true;
    }

    @Override
    public boolean initiatePayment(PaymentRequest paymentRequest) {
        System.out.println("[PaytmGateway] Initiating Payment of " + paymentRequest.getAmount() +
                " " + paymentRequest.getCurrency() + " for " + paymentRequest.getSender());

        return bankingSystem.processPayment(paymentRequest.getAmount());
    }

    @Override
    public boolean confirmPayment(PaymentRequest paymentRequest) {
        System.out.println("[Paytm] Confirming Payment for " + paymentRequest.getSender());
        return true;
    }

}
