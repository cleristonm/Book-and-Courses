package com.keko.code;

public class PrimeGenerator extends Thread {
	
	public static void main(String[] args) {
		PrimeGenerator task = new PrimeGenerator();
		task.start();
		try {			
			Thread.sleep(5000);
			task.printThreadStatus();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
		task.printThreadStatus();
	}
	
	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime\n", number);
			}
			
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted");
				return;
			}
			number++;
		}
	}
	
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public void printThreadStatus() {
		System.out.printf("Main: Status of the Thread : %s\n", this.getState());
		System.out.printf("Main: isInterrupted : %s\n", this.isInterrupted());
		System.out.printf("Main: isAlive : %s\n", this.isAlive());
		
	}
}
