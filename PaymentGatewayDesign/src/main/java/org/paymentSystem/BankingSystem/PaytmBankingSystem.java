package org.paymentSystem.BankingSystem;

import java.util.Random;

public class PaytmBankingSystem implements BankingSystem {
    private Random random = new Random();

    public PaytmBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
        int randomInt = random.nextInt(100);

        return randomInt < 80;
    }
}
