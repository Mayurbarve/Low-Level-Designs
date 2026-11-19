package com.mayur.parking.model;

import java.util.Objects;

public final class ParkingTicket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final long entryTimestamp;
    private final Vehicle vehicle;
    private long exitTimestamp;

    public ParkingTicket(String ticketId, ParkingSpot spot, long entryTimestamp, Vehicle vehicle) {
        this.ticketId = Objects.requireNonNull(ticketId);
        this.spot = Objects.requireNonNull(spot);
        this.entryTimestamp = entryTimestamp;
        this.vehicle = Objects.requireNonNull(vehicle);
    }

    public String getTicketId() { return ticketId; }
    public ParkingSpot getSpot() { return spot; }
    public long getEntryTimestamp() { return entryTimestamp; }
    public long getExitTimestamp() { return exitTimestamp; }
    public Vehicle getVehicle() { return vehicle; }
    public boolean isActive() { return exitTimestamp == 0L; }

    public void markExited(long timestamp) {
        if (!isActive()) throw new IllegalStateException("Ticket is already closed");
        if (timestamp < entryTimestamp) throw new IllegalArgumentException("Exit time cannot precede entry time");
        exitTimestamp = timestamp;
    }
}
