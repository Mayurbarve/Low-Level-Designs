package com.chikueats.strategies;

public class NetBankingPaymentStrategy implements IPaymentStrategy {
    private final String bankName;

    public NetBankingPaymentStrategy(String bankName) { this.bankName = bankName; }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Net Banking (" + bankName + ")");
    }
}
