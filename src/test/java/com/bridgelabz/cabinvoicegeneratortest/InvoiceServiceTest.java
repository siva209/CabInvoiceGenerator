package com.bridgelabz.cabinvoicegeneratortest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.invoiceGenerator.CabRides;
import com.bridgelabz.invoiceGenerator.InvoiceGenerator;
import com.bridgelabz.invoiceGenerator.InvoiceSummary;
import com.bridgelabz.invoiceGenerator.Ride;
import com.bridgelabz.invoiceGenerator.RideRepository;

public class InvoiceServiceTest {
	
	
	
	InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
	
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 3.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(35, fare, 0.0);
	}
	@Test
	public void givenLessDistanceOrTime_ShouldReturnMinFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 0.1;
		int time = 2;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}
	
	public void givenMultipleRides_shouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(CabRides.NORMAL,3.0, 5),
				         new Ride(CabRides.NORMAL, 0.1, 2)
		               };
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
		InvoiceSummary excpectedInvoiceSummary = new InvoiceSummary(2, 40.0);
		assertEquals(excpectedInvoiceSummary,invoiceSummary);	
	}
	@Test
	public void givenUserIDAndNormalRides_shouldReturn_InvoiceSummary() {
		String userId = "Shivam";
		Ride[] rides = { new Ride(CabRides.NORMAL, 3.0, 5),
		                 new Ride(CabRides.NORMAL, 0.1, 2)
                       };
		RideRepository rideRepository = new RideRepository();
		rideRepository.addRides(userId, rides);
		invoiceGenerator.setRideRepository(rideRepository);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 40.0);
		assertEquals(expectedInvoiceSummary, summary);
	}
	@Test
	public void givenUserIDAndMultipleRidesWithMinimumPremiumRide_shouldReturn_InvoiceSummary() {
		String userId = "Sravani";
		Ride[] rides = { new Ride(CabRides.NORMAL, 3.0, 5),
		                 new Ride(CabRides.PREMIUM, 0.1, 2)
                       };
		RideRepository rideRepository = new RideRepository();
		rideRepository.addRides(userId, rides);
		invoiceGenerator.setRideRepository(rideRepository);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 55.0);
		assertEquals(expectedInvoiceSummary, summary);
	}
	@Test
	public void givenUserIDAndMultipleRidesWithMinimumNormalRide_shouldReturn_InvoiceSummary() {
		String userId = "Siva";
		Ride[] rides = { new Ride(CabRides.PREMIUM, 3.0, 5),
		                 new Ride(CabRides.NORMAL, 0.1, 2)
                       };
		RideRepository rideRepository = new RideRepository();
		rideRepository.addRides(userId, rides);
		invoiceGenerator.setRideRepository(rideRepository);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
		assertEquals(expectedInvoiceSummary, summary);
	}
	@Test
	public void givenUserIDAndPremiumRides_shouldReturn_InvoiceSummary() {
		String userId = "Siva";
		Ride[] rides = { new Ride(CabRides.PREMIUM, 3.0, 5),
		                 new Ride(CabRides.PREMIUM, 0.1, 2)
                       };
		RideRepository rideRepository = new RideRepository();
		rideRepository.addRides(userId, rides);
		invoiceGenerator.setRideRepository(rideRepository);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 75.0);
		assertEquals(expectedInvoiceSummary, summary);
	}	
}

