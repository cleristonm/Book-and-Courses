package com.keko.code;

public class Event implements Comparable<Event> {
	private final int thread;
	private final int priority;

	public Event(int thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}

	@Override
	public int compareTo(Event e) {
		if (this.priority > e.getPriority()) {
			return -1;
		}else if (this.priority < e.getPriority()) {
			return 1;
		}else {
			return 0;
		}		
	}
	
	public int getThread() {
		return thread;
	}
	
	public int getPriority() {
		return priority;
	}

}
