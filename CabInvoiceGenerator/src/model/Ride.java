package model;

public class Ride {
    public double distance;
    public int time;
    public RideType type;

    public Ride(double distance, int time, RideType type) {
        this.distance = distance;
        this.time = time;
        this.type = type;
    }
}