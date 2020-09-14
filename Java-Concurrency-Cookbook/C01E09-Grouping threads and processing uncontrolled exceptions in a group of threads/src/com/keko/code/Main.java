package com.keko.code;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		int numberOfThreads = 2 * Runtime.getRuntime().availableProcessors();
		
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup :D");
		Task task = new Task();
		for (int i = 0; i<numberOfThreads; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
		
		System.out.println("Number of Threads: "+ threadGroup.activeCount());
		System.out.println("Information about the Thread Group");
		threadGroup.list();
		
		Thread[] threads = new Thread[threadGroup.activeCount()];
		
		threadGroup.enumerate(threads);
		boolean allFinished = false;
		while (allFinished == false) {
			System.out.println("\n\n**** Thread Status *****");
			allFinished = true;
			for (int i = 0; i < threadGroup.activeCount(); i++) {
				System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
				if (threads[i].getState().equals(Thread.State.TERMINATED) == false) {
					allFinished = false;
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
