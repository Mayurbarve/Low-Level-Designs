package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
    String name();
}
