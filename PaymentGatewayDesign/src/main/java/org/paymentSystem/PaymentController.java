package org.paymentSystem;

import org.paymentSystem.GatewayFactory.GatewayFactory;
import org.paymentSystem.GatewayFactory.GatewayType;
import org.paymentSystem.PaymentGatways.PaymentGateway;

public enum PaymentController {
    INSTANCE;

    public boolean handlePayment(GatewayType type , PaymentRequest paymentRequest) {
        PaymentGateway paymentGateway = GatewayFactory.getInstance().getGateway(type);
        return PaymentService.INSTANCE.processPayment(paymentGateway, paymentRequest);
    }
}
