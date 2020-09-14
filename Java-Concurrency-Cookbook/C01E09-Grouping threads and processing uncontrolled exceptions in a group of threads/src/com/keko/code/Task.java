package com.keko.code;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result= 1000 / ((int) (random.nextInt(200)*10000000));
			if (Thread.currentThread().isInterrupted()) {				
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}

	}

}
