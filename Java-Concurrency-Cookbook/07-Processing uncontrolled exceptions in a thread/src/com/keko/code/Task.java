package com.keko.code;

import java.util.Random;

public class Task implements Runnable {

	@Override
	public void run() {
		Random random = new Random();
		System.out.println("Before the error");
		int numero=Integer.parseInt("TTT");
		System.out.println("This code will not be executed");
		
		
		
		
	}

}
