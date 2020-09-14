package com.keko.code;

import java.util.concurrent.Phaser;

public class Main {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		FileSearch system = new FileSearch("/var/log", "log", phaser);
		FileSearch home = new FileSearch("/home", "log", phaser);
		FileSearch tmp = new FileSearch("/tmp", "log", phaser);
		
		Thread systemThread=new Thread(system,"System");
		systemThread.start();
		
		Thread homeThread=new Thread(home,"Home");
		homeThread.start();
		
		Thread tmpThread=new Thread(tmp,"Temp");
		tmpThread.start();
		
		try {
			systemThread.join();
			homeThread.join();
			tmpThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Terminated: "+ phaser.isTerminated());
		
		
		
	}

}
