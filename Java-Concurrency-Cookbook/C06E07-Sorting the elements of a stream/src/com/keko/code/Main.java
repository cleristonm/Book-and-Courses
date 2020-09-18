package com.keko.code;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		int[] numbers = { 9, 8, 7, 2, 5, 1, 3, 3, 4, 4, 8, 4, 7, 1, 3, 9, 9, 5, 2, 1, 3 };
		Arrays.stream(numbers).parallel().sorted().forEachOrdered(n -> {
			System.out.println(n);
		});

		List<Person> persons = PersonGenerator.generatePersonList(10);
		persons.parallelStream().sorted().forEachOrdered(p -> {
			System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName());
		});
		
		TreeSet<Person> personSet=new TreeSet<>(persons);
		for (int i=0; i<10; i++) {
			System.out.printf("************** %s **************\n", i);
			Person person= personSet.stream().parallel().limit(1)
					.collect(Collectors.toList()).get(0);
			System.out.printf("%s %s\n", person.getFirstName(),
								person.getLastName());		
			
			person=personSet.stream().unordered().parallel().limit(1)
					.collect(Collectors.toList()).get(0);
			System.out.printf("%s %s\n", person.getFirstName(),
					person.getLastName());
		}
		
		

	}

}
