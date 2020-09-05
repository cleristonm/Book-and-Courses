package com.keko.code;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		if (args.length > 1) {
			FileSearch searcher = new FileSearch(args[0], args[1]);
			Thread thread = new Thread(searcher);
			thread.start();
			
			try {
				TimeUnit.SECONDS.sleep(10);
			}catch (Exception e) {
				e.printStackTrace();
			}
			thread.interrupt();
		}
	}

}
