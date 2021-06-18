package com.bridgelabz.cabinvoicegeneratortest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.invoiceGenerator.InvoiceGenerator;
import com.bridgelabz.invoiceGenerator.InvoiceSummary;
import com.bridgelabz.invoiceGenerator.Ride;

public class InvoiceServiceTest {
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
	
	@Test
	public void givenMultipleRides_shouldReturnInvoiceSummary() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		Ride[] rides = { new Ride(2.0, 5),
				         new Ride(0.1, 1)
		               };
		InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
		InvoiceSummary expectedInvoiceSummary=new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary,summary);	
	}
	
	@Test
	public void givenUserIDAndRides_shouldReturn_InvoiceSummary() {
		String userId = "Siva";
		Ride[] rides = { new Ride(3.0, 5),
		                 new Ride(0.1, 2)
                       };
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		invoiceGenerator.addRides(userId, rides);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 40.0);
		assertEquals(expectedInvoiceSummary, summary);
	}
}

