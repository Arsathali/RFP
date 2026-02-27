package service;

import model.*;

public class InvoiceGenerator {

    private static final int NORMAL_COST_PER_KM = 10;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final int NORMAL_MIN_FARE = 5;

    private static final int PREMIUM_COST_PER_KM = 15;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final int PREMIUM_MIN_FARE = 20;

    public double calculateFare(Ride ride) {

        int costPerKm;
        int costPerMin;
        int minFare;

        if (ride.type == RideType.NORMAL) {
            costPerKm = NORMAL_COST_PER_KM;
            costPerMin = NORMAL_COST_PER_MIN;
            minFare = NORMAL_MIN_FARE;
        } else {
            costPerKm = PREMIUM_COST_PER_KM;
            costPerMin = PREMIUM_COST_PER_MIN;
            minFare = PREMIUM_MIN_FARE;
        }

        double fare = ride.distance * costPerKm + ride.time * costPerMin;
        return Math.max(fare, minFare);
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;

        for (Ride ride : rides) {
            totalFare += calculateFare(ride);
        }

        return totalFare;
    }

    public InvoiceSummary generateInvoice(Ride[] rides) {
        double totalFare = calculateFare(rides);
        return new InvoiceSummary(rides.length, totalFare);
    }
}