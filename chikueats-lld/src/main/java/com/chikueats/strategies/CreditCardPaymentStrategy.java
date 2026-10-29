package com.chikueats.strategies;

public class CreditCardPaymentStrategy implements IPaymentStrategy {
    private final String cardNumber;

    public CreditCardPaymentStrategy(String cardNumber) { this.cardNumber = cardNumber; }

    @Override
    public void pay(double amount) {
        String masked = "XXXX-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        System.out.println("Paid Rs." + amount + " using Credit Card " + masked);
    }
}
