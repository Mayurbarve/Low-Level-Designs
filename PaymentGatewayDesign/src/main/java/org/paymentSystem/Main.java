package org.paymentSystem;

import org.paymentSystem.GatewayFactory.GatewayType;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        PaymentRequest req1 = new PaymentRequest("Mayur", "Yasha", 4000.00, "INR");

        System.out.println("processing via Paytm");
        boolean result = PaymentController.INSTANCE.handlePayment(GatewayType.PAYTM, req1);
        System.out.println("Result: " + (result ? "SUCCESS" : "FAIL"));

        System.out.println("processing via Razorpay");
        boolean res = PaymentController.INSTANCE.handlePayment(GatewayType.RAZORPAY, req1);
        System.out.println("Result: " + (res ? "SUCCESS" : "FAIL"));
    }
}
