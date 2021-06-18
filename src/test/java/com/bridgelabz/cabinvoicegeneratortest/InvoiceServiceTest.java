package com.bridgelabz.cabinvoicegeneratortest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bridgelabz.invoiceGenerator.InvoiceGenerator;
import com.bridgelabz.invoiceGenerator.Ride;

public class InvoiceServiceTest {
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 3.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		assertEquals(35, fare, 0.0);
	}
	@Test
	public void givenLessDistanceOrTime_ShouldReturnMinFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		double distance = 0.1;
		int time = 2;
		double fare = invoiceGenerator.calculateFare(distance, time);
		assertEquals(5, fare, 0.0);
	}
	@Test
	public void givenMultipleRides_shouldReturnTotalFare() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		Ride[] rides = { new Ride(3.0, 5),
				         new Ride(0.1, 2)
		               };
		assertEquals(40, invoiceGenerator.calculateFare(rides), 0.0);	
	}
}

