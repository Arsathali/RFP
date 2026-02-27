package repository;

import model.Ride;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {

    private Map<String, Ride[]> rideData = new HashMap<>();

    public void addRides(String userId, Ride[] rides) {
        rideData.put(userId, rides);
    }

    public Ride[] getRides(String userId) {
        return rideData.get(userId);
    }
}