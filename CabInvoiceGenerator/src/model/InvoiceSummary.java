package model;

public class InvoiceSummary {

    public int totalRides;
    public double totalFare;
    public double averageFare;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.totalRides = totalRides;
        this.totalFare = totalFare;
        this.averageFare = totalFare / totalRides;
    }

    @Override
    public String toString() {
        return "InvoiceSummary{" +
                "totalRides=" + totalRides +
                ", totalFare=" + totalFare +
                ", averageFare=" + averageFare +
                '}';
    }
}