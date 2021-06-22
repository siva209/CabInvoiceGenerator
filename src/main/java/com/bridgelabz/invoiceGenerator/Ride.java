package com.bridgelabz.invoiceGenerator;

public class Ride {
	public double distance;
	public int time;
	public CabRides cabRide;
	public Ride(CabRides cabRide, double distance , int time) {
		this.cabRide = cabRide;
		this.distance = distance;
		this.time = time;
	}
}

