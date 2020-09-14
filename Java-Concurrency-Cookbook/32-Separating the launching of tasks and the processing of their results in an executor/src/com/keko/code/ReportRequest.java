package com.keko.code;

import java.util.concurrent.CompletionService;

public class ReportRequest implements Runnable {
	private final String name;		
	private final CompletionService<String> service;
	
	public ReportRequest(String name, CompletionService<String> service) {
		super();
		this.name = name;
		this.service = service;
	}

	@Override
	public void run() {
		ReportGenerator reportGenerator=new ReportGenerator(name, "Report");
		service.submit(reportGenerator);
	}

}