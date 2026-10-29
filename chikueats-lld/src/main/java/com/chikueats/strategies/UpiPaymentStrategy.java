package com.chikueats.strategies;

public class UpiPaymentStrategy implements IPaymentStrategy {
    private final String upiId;

    public UpiPaymentStrategy(String upiId) { this.upiId = upiId; }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI (" + upiId + ")");
    }
}
