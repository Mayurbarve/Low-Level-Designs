package com.chikueats.factories;

import com.chikueats.models.*;
import com.chikueats.strategies.IPaymentStrategy;

import java.util.List;

public interface IOrderFactory {
    Order createOrder(User user, Cart cart, Restaurant restaurant,
                      List<MenuItem> items, IPaymentStrategy paymentStrategy,
                      double totalCost, String orderType);
}
