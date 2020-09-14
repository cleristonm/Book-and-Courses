package com.keko.code;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	private final CompletionService<String> service;
	/*
	 * The volatile keyword guarantees global ordering on reads and
	 * writes to a variable. This implies that every thread accessing a
     * volatile field will read the variable’s current value instead of using
	 * a cached value.
	 * By marking the variable volatile, the value of a variable is never
	 * cached thread-locally. All reads and writes will go straight to main
	 * memory of Java.
	 */
	private volatile boolean end;
	
	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
	}

	@Override
	public void run() {
		while (!end) {
			try {
				Future<String> result=service.poll(20, TimeUnit.SECONDS);
				if (result!=null) {
					String report=result.get();
					System.out.printf("ReportReceiver: Report Received: %s\n", report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("ReportSender: End");
		}
	}
	
	public void stopProcessing() {
		this.end = true;
	}
}
