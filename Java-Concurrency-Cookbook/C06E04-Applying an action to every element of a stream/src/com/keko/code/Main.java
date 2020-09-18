package com.keko.code;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Person> persons = PersonGenerator.generatePersonList(10);
		persons.parallelStream().forEach(p -> {
			System.out.printf("%s, %s\n", p.getLastName(),
					p.getFirstName());
		});
		
		List<Double> doubles = DoubleGenerator.generateDoubleList(10, 100);
		System.out.printf("Parallel forEachOrdered() with numbers\n");
		doubles.parallelStream().sorted().forEachOrdered( n-> {
			System.out.println(n);
		});
		
		persons.parallelStream().sorted().forEachOrdered( p -> {
			System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName());
		});
		
		doubles.parallelStream()
			.peek( d -> System.out.printf("Step 1: Number: %f\n", d))
			.peek( d -> System.out.printf("Step 2: Number: %f\n", d))
			.forEach( d -> System.out.println("Final Step: Number: "+d));
		
		
		
	}

}
