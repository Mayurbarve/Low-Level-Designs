package org.paymentSystem.GatewayFactory;

import org.paymentSystem.PaymentGatways.PaymentGatewayProxy;
import org.paymentSystem.PaymentGatways.PaymentGateway;
import org.paymentSystem.PaymentGatways.PaytmGateway;
import org.paymentSystem.PaymentGatways.RazorpayGateway;

public class GatewayFactory {
    private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory() {}

    public static GatewayFactory getInstance() {
        return instance;
    }

    public PaymentGateway getGateway(GatewayType type) {
        if (type == null) {
            throw new IllegalArgumentException("Gateway type is required");
        }

        return switch (type) {
            case PAYTM -> new PaymentGatewayProxy(new PaytmGateway(), 3);
            case RAZORPAY -> new PaymentGatewayProxy(new RazorpayGateway(), 1);
        };
    }
}
