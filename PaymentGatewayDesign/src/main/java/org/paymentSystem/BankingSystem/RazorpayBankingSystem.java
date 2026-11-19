package org.paymentSystem.BankingSystem;

import java.util.Random;

public class RazorpayBankingSystem implements BankingSystem {

    private Random random = new Random();

    public RazorpayBankingSystem() {}

    @Override
    public boolean processPayment(double amount) {
        int randomInt = random.nextInt(100);

        return randomInt < 90;
    }
}
