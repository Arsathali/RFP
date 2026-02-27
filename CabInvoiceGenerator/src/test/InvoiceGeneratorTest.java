package test;

import org.junit.jupiter.api.Test;
import model.*;
import service.*;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceGeneratorTest{

     @Test
    public void givenUserId_ShouldReturnCorrectInvoice() {
        InvoiceService service = new InvoiceService();

        Ride[] rides = {
                new Ride(2.0, 5, RideType.NORMAL),  
                new Ride(3.0, 10, RideType.PREMIUM) 
        };

        service.addRides("user1", rides);

        InvoiceSummary summary = service.getInvoice("user1");

        assertEquals(2, summary.totalRides);
        assertEquals(90, summary.totalFare);
        assertEquals(45, summary.averageFare);
    }

}