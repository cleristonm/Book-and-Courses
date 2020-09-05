package com.keko.code;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured \n");
		System.out.printf("Thread: %s \n", t.getId());
		System.out.printf("Exceptions: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.println("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());		
	}

}
