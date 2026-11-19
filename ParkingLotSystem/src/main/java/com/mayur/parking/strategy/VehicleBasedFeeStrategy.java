package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingTicket;
import com.mayur.parking.model.VehicleSize;
import java.util.Map;

public final class VehicleBasedFeeStrategy implements FeeStrategy {
    private static final Map<VehicleSize, Double> HOURLY_RATES = Map.of(
            VehicleSize.SMALL, 1.00,
            VehicleSize.MEDIUM, 2.50,
            VehicleSize.LARGE, 5.00);

    @Override
    public double calculateFee(ParkingTicket ticket) {
        double rate = HOURLY_RATES.get(ticket.getVehicle().getSize());
        return FlatRateFeeStrategy.roundToCents(rate * FlatRateFeeStrategy.billableHours(ticket));
    }

    @Override public String name() { return "VEHICLE_BASED"; }
}
