package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingTicket;

public final class FlatRateFeeStrategy implements FeeStrategy {
    private final double ratePerHour;

    public FlatRateFeeStrategy() { this(2.50); }

    public FlatRateFeeStrategy(double ratePerHour) {
        if (!Double.isFinite(ratePerHour) || ratePerHour < 0) throw new IllegalArgumentException("Rate must be finite and non-negative");
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        return roundToCents(ratePerHour * billableHours(ticket));
    }

    static long billableHours(ParkingTicket ticket) {
        if (ticket.getExitTimestamp() == 0L) throw new IllegalStateException("Ticket must be checked out before calculating its fee");
        long elapsedMillis = Math.max(0L, ticket.getExitTimestamp() - ticket.getEntryTimestamp());
        return Math.max(1L, (elapsedMillis + 3_600_000L - 1L) / 3_600_000L);
    }

    static double roundToCents(double amount) { return Math.round(amount * 100.0) / 100.0; }
    @Override public String name() { return "FLAT_RATE"; }
}
