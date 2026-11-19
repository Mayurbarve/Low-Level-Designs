package org.paymentSystem;

public class PaymentRequest {
    private String sender;
    private String recipient;
    private double amount;
    private String currency;

    public PaymentRequest(String sender, String recipient, double amount, String currency) {
        this.sender = sender;
        this.recipient = recipient;
        this.currency = currency;
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

}
