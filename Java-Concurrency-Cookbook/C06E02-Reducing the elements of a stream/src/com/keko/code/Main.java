package com.keko.code;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class Main {

	public static void main(String[] args) {
		List<Double> numbers = DoubleGenerator.generateDoubleList(10000, 1000);

		/*
		 * The Stream class and the specialized DoubleStream , IntStream , and //
		 * LongStream classes implement some methods that are specialized reduce //
		 * operations. In this case, we'll generate a DoubleStream using the //
		 * DoubleGenerator class and use count() , sum() , average() , max() and //
		 * min() to obtain the number of elements, the sum of all the elements, the
		 * average // of all the elements, the maximum number in the stream, and the
		 * minimum // number in the stream. As we can only process the elements of a
		 * stream once, we // have to create a new stream per operation. Take into
		 * account that these methods // are only present in the DoubleStream ,
		 * IntStream , and LongStream classes. // The Stream class only has the count()
		 * method. Some of these methods return // an optional object. Take into account
		 * this object could not have any value, so you // should check before obtaining
		 * the value:
		 */
		DoubleStream doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		long numberOfElements = doubleStream.parallel().count();
		System.out.printf("The list of numbers has %d elements.\n", numberOfElements);
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double sum = doubleStream.parallel().sum();
		System.out.printf("Its numbers sum %f.\n", sum);
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double average = doubleStream.parallel().average().getAsDouble();
		System.out.printf("Its numbers have an average value of %f.\n", average);
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double max = doubleStream.parallel().max().getAsDouble();
		System.out.printf("The maximum value in the list is %f.\n", max);
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double min = doubleStream.parallel().min().getAsDouble();
		System.out.printf("The minimum value in the list is %f.\n", min);

		/*
		 * we'll use the first version of the reduce() method. This method receives as
		 * parameter an associative BinaryOperator that receives two objects of the same
		 * type and returns an object of that type. When the operation has processed all
		 * the elements of the Stream , it returns an Optional object parameterized with
		 * the same type. For example, we'll use this version to calculate the sum of
		 * both the coordinates of a random list of Point objects:
		 */
		List<Point> points = PointGenerator.generatePointList(10000);
		Optional<Point> point = points.parallelStream().reduce((p1, p2) -> {
			Point p = new Point();
			p.setX(p1.getX() + p2.getX());
			p.setY(p1.getY() + p2.getY());
			return p;
		});
		System.out.println(point.get().getX() + ":" + point.get().getY());

		/*
		 * Then, we'll use the second version of the reduce() method. It's similar to
		 * the previous one, but in this case, in addition to the associative
		 * BinaryOperator object, it receives the identity value for that operator (for
		 * example 0 for a sum or 1 for a product) and returns an element of the type
		 * we're working with. If the stream has no values, the identity value will be
		 * returned. In this case, we use this version of the reduce() method to
		 * calculate the total amount of money we need to spend in salaries. We use the
		 * map() method to convert each Person object in an int value (its salary) so
		 * our Stream object will have int values when it executes the reduce() method.
		 * You will get more information about the map() method in the Transforming the
		 * elements of a stream recipe:
		 */
		System.out.printf("Reduce, second version\n");
		List<Person> persons = PersonGenerator.generatePersonList(10000);
		long totalSalary = persons.parallelStream().map(p -> p.getSalary()).reduce(0, (s1, s2) -> s1 + s2);
		System.out.printf("Total salary: %d\n", totalSalary);

		/*
		 * we'll use the third version of the reduce() method. This version is used when
		 * the type of result of the reduce operation is different from the type of
		 * stream elements. We have to provide the identity value of the return type, an
		 * accumulator that implements the BiFunction interfaces and will receive an
		 * object of the return type, an element of the stream to generate a value of
		 * the return type, and a combiner function that implements the BinaryOperator
		 * interface and receives two objects of the return type to generate an object
		 * of that type. In this case, we have used this version of the method to
		 * calculate the number of persons with a salary higher than 50,000 in a list of
		 * random persons:
		 */
		
		Integer value=0;
		value=persons.parallelStream().reduce(value, (n,p) -> {
		if (p.getSalary() > 50000) {
		return n+1;
		} else {
		return n;
		}
		}, (n1,n2) -> n1+n2);
		System.out.printf("The number of people with a salary bigger that 50,000 is %d\n",value);


	}

}
