package com.keko.code;

import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable {
	
	private final ConcurrentSkipListMap<String, Contact> map;
	private final String id;
	
	public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
		super();
		this.map = map;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i=0; i<1000; i++) {
			Contact contact=new Contact(id, String.valueOf(i+1000));
			map.put(id+contact.getPhone(), contact);
		}
	}
	
	

}
