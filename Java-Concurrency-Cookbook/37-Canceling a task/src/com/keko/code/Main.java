package com.keko.code;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ArrayGenerator generator=new ArrayGenerator();
		int array[]=generator.generateArray(1000);
		
		TaskManager manager=new TaskManager();
		
		ForkJoinPool pool=new ForkJoinPool();
		
		SearchNumberTask task=new SearchNumberTask(array,0,1000,5,manager);
		
		pool.execute(task);

		pool.shutdown();
		
		
		// Wait for the finalization of the task
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Write a message to indicate the end of the program
		System.out.printf("Main: The program has finished\n");
	}

}
