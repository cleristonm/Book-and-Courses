package com.keko.code;

public class ParkingStats {
	private long numberCars;
	private long numberMotorcycles;
	private ParkingCash cash;
	private final Object controlCars, controlMotorcycles;
	
	public ParkingStats(ParkingCash cash) {
		numberCars = 0;
		numberMotorcycles = 0;
		controlCars=new Object();
		controlMotorcycles= new Object();
		this.cash=cash;
	}
	
	public void carComeIn() {
		synchronized (controlCars) {
			numberCars++;			
		}
	}
	
	public void carGoOut() {
		synchronized (controlCars) {
			numberCars--;			
		}
		cash.vehiclePay();
	}
	/*
	 * When you use the synchronized keyword to protect a block of code, you use an object as a
	 * parameter. JVM guarantees that only one thread can have access to all the blocks of code
     * protected with this object (note that we always talk about objects, not classes).
	 */
	public void motoComeIn() {
		synchronized (controlMotorcycles) {
			numberMotorcycles++;
		}		
	}
	
	public void motoGoOut() {
		synchronized (controlMotorcycles) {
			numberMotorcycles--;	
		}
		cash.vehiclePay();
	}

	public long getNumberCars() {
		return numberCars;
	}

	public void setNumberCars(long numberCars) {
		this.numberCars = numberCars;
	}

	public long getNumberMotorcycles() {
		return numberMotorcycles;
	}

	public void setNumberMotorcycles(long numberMotorcycles) {
		this.numberMotorcycles = numberMotorcycles;
	}
	
	

}
