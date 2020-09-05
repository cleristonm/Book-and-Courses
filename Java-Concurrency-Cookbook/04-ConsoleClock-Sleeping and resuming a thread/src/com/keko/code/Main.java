package com.keko.code;

import java.util.concurrent.TimeUnit;

/*
 * When the thread is sleeping and is interrupted, the method throws an
 * InterruptedException exception immediately and doesn't wait until the 
 * sleeping time is finished.
 * 
 */
public class Main {

	public static void main(String[] args) {
		ConsoleClock clock = new ConsoleClock();
		Thread thread = new Thread(clock);
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
	}

}
