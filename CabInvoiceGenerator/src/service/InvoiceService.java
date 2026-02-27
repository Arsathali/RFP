package service;

import model.*;
import repository.RideRepository;

public class InvoiceService {

    private RideRepository repository;
    private InvoiceGenerator generator;

    public InvoiceService() {
        this.repository = new RideRepository();
        this.generator = new InvoiceGenerator();
    }

    public void addRides(String userId, Ride[] rides) {
        repository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoice(String userId) {
        Ride[] rides = repository.getRides(userId);
        return generator.generateInvoice(rides);
    }
}