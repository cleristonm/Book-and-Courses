package com.keko.code;

public class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown as Exception \n", t.getId());
		e.printStackTrace(System.out);
		System.out.println("Terminating the rest of the Threads\n");
		interrupt();
	}

	
}
