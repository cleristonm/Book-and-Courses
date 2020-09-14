package com.keko.code;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ForkJoinPool pool=new ForkJoinPool();
		
		FolderProcessor var=new FolderProcessor("/var", "log");
		FolderProcessor home=new FolderProcessor("/home", "log");
		FolderProcessor temp=new FolderProcessor("/tmp","log");
		
		pool.execute(var);
		pool.execute(home);
		pool.execute(temp);
		
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Active Threads: %d\n",
			pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",
			pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",
			pool.getStealCount());
			
			System.out.printf(" var folder is%s done\n", (var.isDone() ? " " : " not "));
			System.out.printf(" temp folder is%sdone\n", (temp.isDone() ? " " : " not "));
			System.out.printf(" home folder is%s done\n", (home.isDone() ? " " : " not "));
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		} while ((!var.isDone())||(!home.isDone())||
				(!temp.isDone()));
		
		pool.shutdown();
		
		List<String> results;
		
		results=var.getResultList();
		System.out.printf("var: %d files found.\n", results.size());
		
		results=temp.getResultList();
		System.out.printf("temp: %d files found.\n",results.size());
		
		results=home.getResultList();
		System.out.printf("home: %d files found.\n", results.size());
		
	}

}
